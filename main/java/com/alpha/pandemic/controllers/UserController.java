package com.alpha.pandemic.controllers;

import com.alpha.pandemic.beans.vo.LoginVo;
import com.alpha.pandemic.beans.vo.RoleVo;
import com.alpha.pandemic.beans.vo.UserVo;
import com.alpha.pandemic.models.AliOss;
import com.alpha.pandemic.models.User;
import com.alpha.pandemic.services.OssService;
import com.alpha.pandemic.services.UserService;
import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.structor.form.UserForm;
import com.alpha.pandemic.structor.security.LoginUser;
import com.alpha.pandemic.structor.tools.UserUtil;
import com.alpha.pandemic.structor.tools.WordUtil;
import com.alpha.pandemic.utils.constant.Constant;
import com.alpha.pandemic.utils.exception.BindingResultException;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController
{
    private final UserService userService;
    private final AliOss aliOss;
    private final OssService ossService;


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:queryOne')")
    @EndPointController(operation = "Getting the user by id",systemMessage = "getting the user by id is not done")
    public FinalResult getOneById(@PathVariable("id") Long id)
    {
        User user= userService.getById(id);
        if(user !=null)
        {
            return FinalResult.ok().data(user);
        }
        else throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST);
    }


    @PostMapping("/login")
    @PreAuthorize("hasAuthority('user:login')")
    @EndPointController(operation = "Getting the user list by page",systemMessage = "getting the user list is not done")
    public FinalResult login(@Validated LoginVo loginVo,  BindingResult result, HttpServletRequest request)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return userService.login(loginVo, request);
    }

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('user:query')")
    @EndPointController(operation = "Getting the user list by page",systemMessage = "getting the user list is not done")
    public FinalResult getUserListPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                       @RequestParam(value = "size",defaultValue = "9") Integer size)
    {
        Page<UserVo> pageInfo = userService.getUserListPage(page, size);
        return FinalResult.ok().data(pageInfo);
    }

    @PostMapping("/search")
    @PreAuthorize("hasAuthority('user:query')")
    @EndPointController(operation = "Searching the user by page",systemMessage = "getting the user by page is not done")
    public FinalResult searchUserListPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                          @RequestParam(value = "size",defaultValue = "9") Integer size,
                                          @RequestBody UserForm userForm)
    {
        Page<UserVo> pageInfo = userService.userSeachListPage(userForm,page, size);
        return FinalResult.ok().data(pageInfo);
    }

    @PutMapping("/forbidden")
    @PreAuthorize("hasAuthority('user:status')")
    @ApiOperation(value = "Updating the user status", notes = "Updating the user status")
    public FinalResult updateStatus(@RequestParam("id") Long id, @RequestParam("forbidden") Boolean forbidden)
    {
        LoginUser loginUser = UserUtil.getCurrentLoginUser();
        assert loginUser != null;
        if(loginUser.getId().equals(id))
        {
            throw  new BusinessException("The current user can't operate");
        }
        User user = userService.getById(id);
        if(Constant.ADMIN.equals(user.getType()))
        {
            throw new BusinessException("The current user is not an administrator");
        }
        int res = userService.updateUserStatusById(id, forbidden ? Constant.FORBIDDEN : Constant.ACTIVE);
        String message = forbidden ? "Blocked" : "Activated";
        if(res ==1){ return FinalResult.ok().message(message+ "accound");}

        else{throw new BusinessException(ResultCode.ACCOUNT_HAS_CHINESE);}
    }

    @PutMapping("/password/{id}")
    @PreAuthorize("hasAuthority('user:resetPassword')")
    @ApiOperation(value = "Resetting the password", notes = "Resetting the password")
    @EndPointController(operation = "Resetting the password",systemMessage = "Resetting the password has failed")
    public FinalResult resetPassword(@PathVariable("id") Long id)
    {

        int res = userService.resetPassword(id);
        if(res ==1){ return FinalResult.ok().message("The password has been reset");}

        else{throw new BusinessException(ResultCode.REST_ACCOUNT_PWD.getCode(),"Resetting the password has failed");}
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('user:add')")
    @ApiOperation(value = "adding the new user", notes = "The new user info")
    @EndPointController(operation = "Adding a new user",systemMessage = "Adding a new user has occured")
    public FinalResult addUser(@Validated UserVo uservo,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        if(userService.checkUserExist(uservo.getUsername()))
        {
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        }
        if(WordUtil.isContainChinese(uservo.getUsername()))
        {
            throw new BusinessException(ResultCode.ACCOUNT_HAS_CHINESE);
        }
        boolean b = userService.save(UserUtil.executeEntity(uservo, null));

        if(b){return FinalResult.ok().message("You have successfully added a new user");}
        else {throw new BusinessException(ResultCode.ADD_ACCOUNT.getCode(),"Adding new user has failed");}
    }

    @GetMapping("/avatar")
    @ApiOperation(value = "Getting the avatar", notes = "Getting the avatar")
    public FinalResult getUserAvatar(@RequestParam("username") String username)
    {
        String avatar = userService.getAvatarByUsername(username);
        return FinalResult.ok().message("The just got the avatar").data(avatar);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('user:edit')")
    @ApiOperation(value = "editing the user", notes = "Editing the user info")
    @EndPointController(operation = "Editing the user",systemMessage = "Editing the user has failed")
    public FinalResult editUser(@PathVariable("id")Long id, @Validated UserVo uservo,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        User user = userService.getById(id);
        if(!user.getUsername().equals(uservo.getUsername()))
        {
            boolean userExist =userService.checkUserExist(uservo.getUsername());
            if(userExist)
            {
                throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
            }
        }
        if(WordUtil.isContainChinese(uservo.getUsername()))
        {
            throw new BusinessException(ResultCode.ACCOUNT_HAS_CHINESE);
        }
        boolean update = userService.updateById(UserUtil.executeEntity(uservo, id));

        if(update){return FinalResult.ok().message("You have successfully updated the user");}
        else {throw new BusinessException(ResultCode.EDIT_ACCOUNT.getCode(),"Updating the user has failed");}
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    @ApiOperation(value = "delete user", notes = "delete user")
    @EndPointController(operation = "Delete the user",systemMessage = "Deleting the user has failed")
    public FinalResult deleteUser(@PathVariable("id")Long id)
    {
        User user = userService.getById(id);
        if(Objects.isNull(user))
        {
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        LoginUser loginUser =UserUtil.getCurrentLoginUser();
        assert loginUser != null;
        if(loginUser.getId().equals(id))
        {
            throw new BusinessException(ResultCode.CURRENT_USER_CANNOT_DO);
        }
        if(user.getType().equals(Constant.ADMIN))
        {
            throw new BusinessException(ResultCode.CURRENT_USER_CANNOT_DELETE_ADMIN);
        }
        boolean  deleteUser = userService.removeById(id);
        if(deleteUser)
        {
            if(user.getAvatar() != null)
            {
                String fileName = user.getAvatar().replaceFirst(aliOss.getUrlPrefix(),"");
                ossService.deleteFile(fileName);

            }
            return FinalResult.ok().message("You have successfully delete the user");
        }
        else {throw new BusinessException("deleting the user has failed");}
    }

    @PutMapping("/export")
    @PreAuthorize("hasAuthority('user:export')")
    @ApiOperation(value = "Exporting the user list", notes = "Exporting the user info")
    @EndPointController(operation = "Exporting the user",systemMessage = "Exporting the user has failed")
    public void exportUser(HttpServletResponse response, UserForm userForm,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        userService.exportUserList(response, userForm);

    }

    @PutMapping("/self/password")
    @ApiOperation(value = "Resetting self password", notes = "Resetting self password")
    @EndPointController(operation = "Resetting the password",systemMessage = "Resetting the password has failed")
    public FinalResult resetSelfPassword(@Validated UserVo changepwd,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return userService.resetSelfPassword(changepwd);
    }

    @GetMapping("/capcha/{historyId}")
    @ApiOperation(value = "Getting the capcha image", notes = "Getting the capcha image")
    public FinalResult getCapchaImage(@PathVariable("historyId") String historyId) throws IOException
    {
        return userService.getCapchaImage(historyId);
    }

    @GetMapping("/{id}/roles")
    @PreAuthorize("hasAuthority('user:queryRole')")
    @ApiOperation(value = "Getting the role of the user", notes = "Getting the role of the user")
    public FinalResult getRoles(@PathVariable("id")Long id)
    {
        List<Long> roleIds = userService.getRolesById(id);
        return FinalResult.ok().data(roleIds);
    }

    @PostMapping("/{id}/roles")
    @PreAuthorize("hasAuthority('user:assign')")
    @ApiOperation(value = "Assign role to the user", notes = "Assign role to user")
    @EndPointController(operation = "Assign role to the user",systemMessage = "Assign role to the user has failed")
    public FinalResult assignRoles(@PathVariable("id") Long id, RoleVo roleVo)
    {
        return userService.assignRoles(id,roleVo);
    }
}
