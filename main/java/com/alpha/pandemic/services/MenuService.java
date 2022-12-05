package com.alpha.pandemic.services;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alpha.pandemic.beans.vo.MenuExportVo;
import com.alpha.pandemic.beans.vo.MenuVo;
import com.alpha.pandemic.mappers.MenuMapper;
import com.alpha.pandemic.models.Menu;
import com.alpha.pandemic.structor.convertor.MenuConverter;
import com.alpha.pandemic.structor.form.MenuForm;
import com.alpha.pandemic.structor.security.LoginUser;
import com.alpha.pandemic.structor.tools.ExcelStyleUtil;
import com.alpha.pandemic.structor.tools.ExcelUtil;
import com.alpha.pandemic.structor.tools.MenuTreeBuilderUtil;
import com.alpha.pandemic.structor.tools.UserUtil;
import com.alpha.pandemic.utils.constant.Constant;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MenuService extends ServiceImpl<MenuMapper, Menu>
{
    private final MenuMapper menuMapper;
    private final List<Long> openIds = new ArrayList<>();

    public List<Long> getOpendIds()
    {
        return openIds;
    }

    public List<MenuVo> findMenuTree()
    {
        List<Menu> menusList = this.baseMapper.selectList(null);
        openIds.clear();
        menusList.forEach(menu ->
        {
            if(menu.getOpen().equals(Constant.OPEN))
            {
                openIds.add(menu.getId());
            }
        });
        List<MenuVo> menuNodes= MenuConverter.converterToMenus(menusList);
        return MenuTreeBuilderUtil.build(menuNodes);
    }

    public FinalResult findMenusAndUrls()
    {
        LoginUser loginUser = UserUtil.getCurrentLoginUser();
        assert loginUser != null;
        List<Menu> list = loginUser.getMenus();
        List<String> urls = list.parallelStream().map(Menu::getUrl).filter(Objects::nonNull).collect(Collectors.toList());
        List<MenuVo> menuNodes= MenuConverter.converterToMenus(list);
        Map<String, Object> tree = new HashMap<>(2);
        urls.add("/main");
        urls.add("/welcome");
        urls.add("/common/404");
        tree.put("urls",urls);
        return FinalResult.ok().data(tree);
    }

    @Transactional(rollbackFor = Exception.class)
    public FinalResult updateMenus( MenuVo menuVo)
    {
        Menu oldMenu = this.baseMapper.selectById(menuVo.getId());
        if(Objects.isNull(oldMenu))
        {
            return FinalResult.error().message(ResultCode.CURRENT_USER_CANNOT_DO.getMessage());
        }
        if(menuVo.getType().equals(Constant.BUTTON))
        {
            Integer count = menuMapper.checkHasChild(menuVo.getId());
            if(count>0)
            {
                return FinalResult.error().message("The current menu hasn't child or not available");
            }
        }
        Integer newStatus = menuVo.getDisabled() ? Constant.FORBIDDEN : Constant.ACTIVE;
        if(!oldMenu.getAvailable().equals(newStatus) && Objects.nonNull(menuVo.getIds()) && !menuVo.getIds().isEmpty())
        {
            menuMapper.updateChildren(menuVo.getIds(), newStatus);
        }
        Menu newMenu = new Menu();
        newMenu.setAvailable(newStatus);
        BeanUtils.copyProperties(menuVo, newMenu);
        int newRow = this.baseMapper.updateById(newMenu);
        if(newRow> 0 )
        {
            return FinalResult.ok();
        }
        else throw new BusinessException(ResultCode.FAILED);
    }

    public FinalResult submitMenu(MenuVo menuVo)
    {
        Menu newMenu = new Menu();
        BeanUtils.copyProperties(menuVo, newMenu);
        newMenu.setAvailable(menuVo.getDisabled() ? Constant.FORBIDDEN : Constant.ACTIVE);
        int newRow = this.baseMapper.insert(newMenu);
        if(newRow> 0 )
        {
            return FinalResult.ok();
        }
        else throw new BusinessException(ResultCode.FAILED);

    }

    public FinalResult deleteMenu(Long id)
    {
        Menu menu = this.baseMapper.selectById(id);
        if(Objects.isNull(menu))
        {
            return FinalResult.error().message("You don't have the authorized to pass this opration");
        }
        Integer count = menuMapper.checkHasChild(id);
        if(count> 0)
        {
            return FinalResult.error().message("You cannot delete this menu children ");
        }
        int deleteRow = menuMapper.deleteById(id);
        if(deleteRow>0)
        {
            return FinalResult.ok().message("You have deleted the menu");
        }
        else throw new BusinessException(ResultCode.FAILED);
    }

    public void exportMenuList(HttpServletResponse response, MenuForm menuForm)
    {
        LambdaQueryWrapper<Menu> wrapper = executeWrapper(menuForm);
        List<Menu> menus = this.baseMapper.selectList(wrapper);

        List<MenuExportVo> menuExport = toMenuExportVo(menus);
        try
        {
            ExportParams params = new ExportParams("Menu list table", "sheet2", ExcelType.XSSF);
            params.setStyle(ExcelStyleUtil.class);
            ExcelUtil.exportExcel(menuExport, MenuVo.class,"Menu list",params,response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BusinessException(ResultCode.EXPORT_FAIL);
        }
    }

    //    These classes are the utils

    private List<MenuExportVo> toMenuExportVo( List<Menu> menus)
    {
        List<MenuExportVo> menuExport = new ArrayList<>(menus.size());
        List<Long> menuEx = new ArrayList<>(menus.size());

        menus.forEach(menu ->
        {
            MenuExportVo exportVo = new MenuExportVo();

            BeanUtils.copyProperties(menu, exportVo);
            exportVo.setType(menu.getType().equals(Constant.MENU)? "Menu" : "Button");
            exportVo.setAvailable(menu.getAvailable().equals(Constant.FORBIDDEN) ? "Block" : "Activated");
            exportVo.setOpen(menu.getOpen().equals(Constant.OPEN) ? "Opened" : "Close");
            menuExport.add(exportVo);

            menuEx.add(menu.getParentId());
        });
        return menuExport;
    }

    private static LambdaQueryWrapper<Menu> executeWrapper( MenuForm menuForm)
    {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        if (null != menuForm.getNameFilter())
        {
            if(menuForm.getNameFilter()!=null)
            {
                wrapper.like(Menu::getMenuName, menuForm.getNameFilter());
            }
        }
        return wrapper;
    }
}
