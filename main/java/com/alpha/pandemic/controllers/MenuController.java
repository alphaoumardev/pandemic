package com.alpha.pandemic.controllers;

import com.alpha.pandemic.beans.vo.MenuVo;
import com.alpha.pandemic.services.MenuService;
import com.alpha.pandemic.structor.form.MenuForm;
import com.alpha.pandemic.utils.exception.BindingResultException;
import com.alpha.pandemic.utils.response.FinalResult;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/menu")
public class MenuController
{
    private final MenuService menuService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('menu:menuList')")
    @ApiOperation(value = "Getting the menu list", notes = "To get the user menu list")
    public FinalResult getMenuList()
    {

        List<MenuVo> menuTree = menuService.findMenuTree();
        List<Long> openId = menuService.getOpendIds();
        Map<String, Object> tree = new HashMap<>(2);
        tree.put("menus",menuTree);
        tree.put("ids", openId);
        return FinalResult.ok().data(tree);
    }

    @GetMapping("/menulist")
    @PreAuthorize("hasAuthority('menu:menus')")
    @ApiOperation(value = "Getting the menus", notes = "To get the menus")
    public FinalResult getMenus()
    {
        return menuService.findMenusAndUrls();
    }

    @PutMapping("/modifymenu")
    @PreAuthorize("hasAuthority('menu:update')")
    @ApiOperation(value = "Update the menus", notes = "To update the menus")
    public FinalResult modifyMenus(@Validated MenuVo menuVo,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return menuService.updateMenus(menuVo);
    }

    @PostMapping("/submit")
    @PreAuthorize("hasAuthority('menu:update')")
    @ApiOperation(value = "Update the menus", notes = "To update the menus")
    public FinalResult submitMenu(@Validated MenuVo menuVo,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return menuService.submitMenu(menuVo);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('menu:delete')")
    @ApiOperation(value = "Delete the menu", notes = "To delete the menu")
    public FinalResult deleteMenu(@PathVariable("id") Long id)
    {
        return menuService.deleteMenu(id);
    }

    @PostMapping("/export")
    @PreAuthorize("hasAuthority('menu:export')")
    @ApiOperation(value = "Export the role list", notes = "To export the user role list")
    public void exportRoleList(HttpServletResponse response, @Validated MenuForm menuForm)
    {
        menuService.exportMenuList(response, menuForm);
    }
}
