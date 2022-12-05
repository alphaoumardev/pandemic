package com.alpha.pandemic.controllers;

import com.alpha.pandemic.beans.vo.MenuVo;
import com.alpha.pandemic.beans.vo.RoleVo;
import com.alpha.pandemic.models.Role;
import com.alpha.pandemic.services.RoleMenuService;
import com.alpha.pandemic.services.RoleService;
import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.structor.form.RoleForm;
import com.alpha.pandemic.utils.constant.Constant;
import com.alpha.pandemic.utils.exception.BindingResultException;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Api(value = "The user role module", tags = "The user role Api")
public class RoleController
{
    private final RoleService roleService;
    private final RoleMenuService roleMenuService;

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('role:query')")
    @ApiOperation(value = "Getting the role list", notes = "To get the user role list")
    public FinalResult getRoleList(@Validated RoleForm roleForm,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        Page<Role> rolePage = roleService.getList(roleForm);
        return FinalResult.ok().data(rolePage);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('role:add')")
    @ApiOperation(value = "Adding a new role", notes = "Add a new role")
    @EndPointController(systemMessage = "Adding new role has failed", operation = "Adding new role")
    public FinalResult add(@Validated RoleVo roleVo,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        if(roleVo.getRoleCode().equals("ADMIN"))
        {
            throw new BusinessException("You cannot add a new role");
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleVo, role);
        try
        {
            boolean b = roleService.save(role);
            if(b)
            {
                return FinalResult.ok().message("You have added a new role");
            }
            else throw new BusinessException(ResultCode.ADD_ROLE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BusinessException("The role has failed it's may already exists");
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('role:delete')")
    @ApiOperation(value = "Deleting the role", notes = "deleting role")
    @EndPointController(systemMessage = "Deleting the role has failed", operation = "delete role")
    public FinalResult deleteRole(@PathVariable("id") Long id)
    {
        Role role =roleService.getById(id);
        if(!Objects.isNull(role))
        {
            if(role.getRoleCode().equals("ADMIN"))
            {
                throw new BusinessException("You cannot delete the administrator");
            }
        }
        boolean remove = roleService.removeById(id);
        if(remove)
        {
            return FinalResult.ok().message("You have deleted the user role");
        }
        else throw new BusinessException(ResultCode.DELETE_ROLE);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('role:update')")
    @ApiOperation(value = "updating the role", notes = "updating the role")
    @EndPointController(systemMessage = "updating the role has failed", operation = "update role")
    public FinalResult updateRole(@Validated RoleVo roleVo,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        Role roles = roleService.getById(roleVo.getId());
        if(!Objects.isNull(roles))
        {
            if(roles.getRoleCode().equals(roleVo.getRoleCode()))
            {
                throw new BusinessException("You cannot change the role");
            }
        }

        Role role = new Role();
        BeanUtils.copyProperties(roleVo, role);
        boolean b = roleService.updateById(role);
        if(b)
        {
            return FinalResult.ok().message("You have updated the role");
        }
        else throw new BusinessException(ResultCode.EDIT_ACCOUNT);
    }

    @PostMapping("/export")
    @PreAuthorize("hasAuthority('role:export')")
    @ApiOperation(value = "Export the role list", notes = "To export the user role list")
    public void exportRoleList(HttpServletResponse response, @Validated RoleForm roleForm,  BindingResult result) throws IOException
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        roleService.exportRoleList(response, roleForm);
    }

    @PutMapping("/forbidden")
    @PreAuthorize("hasAuthority('role:status')")
    @ApiOperation(value = "Updating the role status", notes = "Updating the role status")
    public FinalResult updateRoleStatus(@RequestParam("id") Long id, @RequestParam("forbidden") Boolean forbidden)
    {
        int res = roleService.updateRoleStatusById(id, forbidden ? Constant.FORBIDDEN : Constant.ACTIVE);
        String message = forbidden ? "Blocked" : "Activated";
        if(res ==1){ return FinalResult.ok().message("The role is "+message);}

        else throw new BusinessException(ResultCode.FORBIDDEN_ACCOUNT.getCode(),"The role is "+message);
    }

    @GetMapping("/allroles")
    @PreAuthorize("hasAuthority('role:queryall')")
    @ApiOperation(value = "Getting the role list", notes = "To get the user role list")
    public FinalResult getAllRoleList()
    {
        List<Role> allRoles = roleService.list();
        return FinalResult.ok().data(allRoles);
    }

    @GetMapping("/role/{id}")
    @PreAuthorize("hasAuthority('role:queryMenuList')")
    @ApiOperation(value = "Getting the role list", notes = "To get the user role list")
    public FinalResult getMenuRoleListById(@PathVariable("id") Long id)
    {
        List<Long> menuRole = roleMenuService.getRoleMenuById(id);
        return FinalResult.ok().data(menuRole);
    }

    @PostMapping("/{id}/menus")
    @PreAuthorize("hasAuthority('role:authority')")
    @ApiOperation(value = "Assigning the role list", notes = "To assign the user role")
    @EndPointController(systemMessage = "updating the role has failed", operation = "update role")
    public FinalResult assingMenus(@PathVariable("id") Long id, MenuVo menuIds)
    {
        return roleService.assignMenus(id, menuIds);
    }
}
