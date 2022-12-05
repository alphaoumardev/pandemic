package com.alpha.pandemic.services;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alpha.pandemic.beans.vo.MenuVo;
import com.alpha.pandemic.beans.vo.RoleVo;
import com.alpha.pandemic.mappers.RoleMapper;
import com.alpha.pandemic.models.Menu;
import com.alpha.pandemic.models.Role;
import com.alpha.pandemic.models.RoleMenu;
import com.alpha.pandemic.structor.form.RoleForm;
import com.alpha.pandemic.structor.tools.ExcelStyleUtil;
import com.alpha.pandemic.structor.tools.ExcelUtil;
import com.alpha.pandemic.utils.constant.Constant;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoleService extends ServiceImpl<RoleMapper, Role>
{
    private final RoleMenuService roleMenuService;
    private final MenuService menuService;

    public Page<Role> getList(RoleForm roleForm)
    {
        LambdaQueryWrapper<Role> wrapper = executeWrapper(roleForm);
        return this.baseMapper.selectPage(new Page<>(roleForm.getPage(), roleForm.getSize()),wrapper);
    }

    public void exportRoleList(HttpServletResponse response, RoleForm roleForm) throws IOException
    {
        LambdaQueryWrapper<Role> wrapper = executeWrapper(roleForm);
        List<Role> roles = this.baseMapper.selectList(wrapper);

        List<RoleVo> roleVos = toRoleExportVo(roles);
        try
        {
            ExportParams params = new ExportParams("Role list table", "sheet2", ExcelType.XSSF);
            params.setStyle(ExcelStyleUtil.class);
            ExcelUtil.exportExcel(roleVos, RoleVo.class,"Role list",params,response);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BusinessException(ResultCode.EXPORT_FAIL);
        }
    }

    public int updateRoleStatusById(Long id, Integer status)
    {
        Role role = this.baseMapper.selectById(id);
        if(!Objects.isNull(role))
        {
            if(role.getRoleCode().equals("ADMIN"))
            {
                throw new BusinessException("You cannot pass this operation");
            }
        }
        return this.baseMapper.updateRoleStatus(id,status);
    }

    public FinalResult assignMenus(Long id,  MenuVo menuIds)
    {
        Long[] mids = menuIds.getMids();
        Role role = this.baseMapper.selectById(id);
        if (Objects.isNull(role))
        {
            throw new BusinessException(ResultCode.ROLE_NOT_EXIST);
        }
        List<Long> menuList = roleMenuService.getRoleMenuById(id);
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId, id);
        boolean res = roleMenuService.remove(wrapper);

        if (!Objects.isNull(mids) && mids.length > 0)
        {
            List<RoleMenu> roleMenus = new ArrayList<>(mids.length);
            for (Long mid : mids)
            {
                Menu menu = menuService.getById(mid);
                if (Objects.isNull(menu))
                {
                    throw new BusinessException(ResultCode.MENU_NOT_EXIST);
                }
                if (menu.getAvailable().equals(Constant.FORBIDDEN))
                {
                    throw new BusinessException(ResultCode.MENU_FORBIDDEN);
                }
                RoleMenu role_menu = new RoleMenu();
                role_menu.setMenuId(mid);
                role_menu.setRoleId(id);
                roleMenus.add(role_menu);
            }
            boolean newResult = roleMenuService.saveBatch(roleMenus);
            if (newResult)
            {
                return FinalResult.ok().message("Has successfully assign the menu to the user");
            }
            else throw new BusinessException(ResultCode.ASSIGN_ROLES_ERROR);
        }
        if(res || menuList.isEmpty())
        {
            return FinalResult.ok().message("You have successfully has remove the menu");
        }
        else throw new BusinessException(ResultCode.REMOVE_ROLES_ERROR);
    }

    //    These classes are the utils

    private List<RoleVo> toRoleExportVo( List<Role> roles)
    {
        List<RoleVo> roleVos = new ArrayList<>(roles.size());
        roles.forEach(role ->
        {
            RoleVo exportVo = new RoleVo();
            BeanUtils.copyProperties(role, exportVo);
            exportVo.setForbidden(role.getForbidden().equals(Constant.FORBIDDEN)? "Block" : "Active");
            roleVos.add(exportVo);
        });
        return roleVos;
    }

    private static LambdaQueryWrapper<Role> executeWrapper( RoleForm roleForm)
    {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (null != roleForm.getRoleName())
        {
            wrapper.like(Role::getRoleName, roleForm.getRoleName());
        }
        return wrapper;
    }
}
