package com.alpha.pandemic.structor.tools;

import com.alpha.pandemic.beans.vo.MenuVo;
import com.alpha.pandemic.utils.constant.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class MenuTreeBuilderUtil
{
    public static List<MenuVo> build(List<MenuVo> menuNodes)
    {
        // getting the Menu according to the order and sort it
        List<MenuVo> rootMenus = menuNodes.stream().filter(menu -> menu.getParentId().equals(Constant.ROOT_MENU)).sorted(MenuVo.order()).collect(Collectors.toList());

        rootMenus.forEach(root ->
        {
            List<MenuVo> child = getChild(root.getId(), menuNodes);
            root.setChildren(child);
        });
        return rootMenus;
    }

    private static List<MenuVo> getChild(Long id, List<MenuVo> nodes)
    {
        List<MenuVo> childList = nodes.stream().filter(node -> node.getParentId().equals(id)).collect(Collectors.toList());
        childList.forEach(child -> child.setChildren(getChild(child.getId(), nodes)));
        childList.sort(MenuVo.order());
        if (childList.isEmpty())
        {
            return new ArrayList<>();
        }
        return childList;
    }
}
