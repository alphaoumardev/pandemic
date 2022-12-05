package com.alpha.pandemic.structor.convertor;

import com.alpha.pandemic.beans.vo.MenuVo;
import com.alpha.pandemic.models.Menu;
import com.alpha.pandemic.utils.constant.Constant;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class MenuConverter
{

    public static List<MenuVo> converterToMenus(List<Menu> menus)
    {
        List<MenuVo> menuNodeVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menus))
        {
            menus.forEach(menu ->
            {
                MenuVo menuNodeVo = new MenuVo();
                BeanUtils.copyProperties(menu, menuNodeVo);
                menuNodeVo.setDisabled(menu.getAvailable().equals(Constant.FORBIDDEN));
                menuNodeVos.add(menuNodeVo);
            });
        }
        return menuNodeVos;
    }
}
