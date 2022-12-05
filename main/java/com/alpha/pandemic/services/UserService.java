package com.alpha.pandemic.services;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alpha.pandemic.beans.vo.LoginVo;
import com.alpha.pandemic.beans.vo.RoleVo;
import com.alpha.pandemic.beans.vo.UserExportVo;
import com.alpha.pandemic.beans.vo.UserVo;
import com.alpha.pandemic.config.RedisCustom;
import com.alpha.pandemic.mappers.UserMapper;
import com.alpha.pandemic.models.Role;
import com.alpha.pandemic.models.User;
import com.alpha.pandemic.models.UserRole;
import com.alpha.pandemic.structor.form.UserForm;
import com.alpha.pandemic.structor.tools.ExcelStyleUtil;
import com.alpha.pandemic.structor.tools.ExcelUtil;
import com.alpha.pandemic.structor.tools.UserUtil;
import com.alpha.pandemic.structor.tools.VerifyCodeUtil;
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
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService extends ServiceImpl<UserMapper,User>
{
    private final RoleService roleService;
    private final RedisService redisService;
    private final LogingLogService logingLogService;
    private final DepartmentService departmentService;
    private final UserRoleService userRoleService;

    public Page<UserVo> getUserListPage(Integer page, Integer size)
    {
        Page<User> pages=this.baseMapper.selectPage(new Page<>(page,size),null);
        return toUserVOPage(pages);
    }

//    @Deprecated
    public FinalResult login( LoginVo loginVo, HttpServletRequest request)
    {
        Long interval = redisService.getKeyInterval(RedisCustom.LOGIN_VERIFY_CODE+loginVo.getVerifyCodeId());
        if(interval==null ||interval <= 3)
        {
            return FinalResult.error().message("The code has expired");
        }
        String code = redisService.getLoginVerifyCode(loginVo.getVerifyCodeId());
        if(!loginVo.getVerifyCode().equalsIgnoreCase(code))
        {
            return FinalResult.error().message("The code is incorrect");
        }
        User user = this.baseMapper.getByUsername(loginVo.getUsername());
        if(user==null)
        {
            return FinalResult.error(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        if (user.getForbidden().equals(Constant.FORBIDDEN))
        {
            return FinalResult.error(ResultCode.USER_ACCOUNT_LOCKED);
        }
        logingLogService.saveLoginLog(loginVo.getUsername(),request);

        if(UserUtil.matches(loginVo.getPassword(),user.getPassword()))
        {
            String deptName= departmentService.getDepartmentNameById(user.getDepartmentId());
            redisService.deleteLoginVerifyCode(loginVo.getVerifyCodeId());
            return FinalResult.ok().data(UserUtil.executeVo(user, deptName));
        }
        else
        {
            return FinalResult.error(ResultCode.USER_CREDENTIALS_ERROR);
        }
    }

    public User getByUsername(String username)
    {
        return baseMapper.getByUsername(username);
    }

    public boolean checkUserExist(String username)
    {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return this.baseMapper.selectCount(wrapper)>0;
    }

    public Page<UserVo> userSeachListPage(UserForm userForm, Integer page, Integer size)
    {
        LambdaQueryWrapper<User> wrapper = executeWrapper(userForm);
        Page<User> pages=this.baseMapper.selectPage(new Page<>(page,size),wrapper);
        return toUserVOPage(pages);
    }

    public int updateUserStatusById(Long id, Integer integer)
    {
        return this.baseMapper.updateUserStatusById(id, integer);
    }

    public int resetPassword(Long id)
    {
        return this.baseMapper.resetPassword(UserUtil.encode(Constant.DEFAULT_PASSWORD),id);
    }

    public String getAvatarByUsername(String username)
    {
        return this.baseMapper.getAvatarByUsername(username);
    }

    public void exportUserList(HttpServletResponse response, UserForm userForm)
    {
        List<UserVo> userList = this.getUserList(userForm);
        List<UserExportVo> userExportVos = UserUtil.exportToVo(userList);
        try
        {
            ExportParams params = new ExportParams("The user list","sheet1", ExcelType.XSSF);
            params.setStyle(ExcelStyleUtil.class);
            ExcelUtil.exportExcel(userExportVos,UserExportVo.class,"User list" , params, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BusinessException(ResultCode.EXPORT_FAIL);
        }
    }

    public FinalResult resetSelfPassword( UserVo changepwd)
    {
        if(changepwd.getOldPassword().equals(changepwd.getNewPassword()))
        {
            return FinalResult.error().message("The two passwords are not coherent");
        }
        User user = this.baseMapper.selectById(changepwd.getId());
        if(Objects.isNull(user))
        {
            return FinalResult.error().message("The user info is incorrect ");
        }
        if(!UserUtil.matches(changepwd.getOldPassword(),user.getPassword()))
        {
            return FinalResult.error().message("The password is incorrect");
        }
        int pass = this.baseMapper.resetPassword(UserUtil.encode(changepwd.getNewPassword()), user.getId());
        if(pass>0)
        {
            return FinalResult.ok();
        }
        else throw new BusinessException(ResultCode.FAILED);
    }

    @Transactional(rollbackFor = Exception.class)
    public FinalResult assignRoles(Long id,  RoleVo roleIds)
    {
        Long[] rids = roleIds.getRids();
        User user = this.baseMapper.selectById(id);
        if (Objects.isNull(user))
        {
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        List<Long> rolesList = this.getRolesById(id);
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, id);
        boolean res = userRoleService.remove(wrapper);

        if (!Objects.isNull(rids) && rids.length > 0)
        {
            List<UserRole> userRoles = new ArrayList<>(rids.length);
            for (Long rid : rids)
            {
                Role role = roleService.getById(rid);
                if (Objects.isNull(role))
                {
                    throw new BusinessException(ResultCode.ROLE_IDS_ERROR);
                }
                if (role.getForbidden().equals(Constant.FORBIDDEN))
                {
                    throw new BusinessException(ResultCode.ROLE_IS_FORBIDDEN);
                }
                UserRole user_role = new UserRole();
                user_role.setUserId(id);
                user_role.setRoleId(rid);
                userRoles.add(user_role);
            }
            boolean newResult = userRoleService.saveBatch(userRoles);
            if (newResult)
            {
                return FinalResult.ok().message("Has successfully assign the role");
            }
            else throw new BusinessException(ResultCode.ASSIGN_ROLES_ERROR);
        }
        if(res || rolesList.isEmpty())
        {
            return FinalResult.ok().message("You have successfully has remove the role");
        }
        else throw new BusinessException(ResultCode.REMOVE_ROLES_ERROR);
    }

    public FinalResult getCapchaImage( String historyId) throws IOException
    {
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        String uuid = UUID.randomUUID().toString().replace("-","");
        if(!historyId.equals("first"))
        {
            redisService.deleteLoginVerifyCode(historyId);
        }
        redisService.setLoginVerifyCode(uuid,verifyCode, RedisCustom.LOGIN_VERIFY_TIME);
        //making the image
        int width=111, height=36;
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        VerifyCodeUtil.outputImage(width,height,byteArray,verifyCode);
        String image = Base64.getEncoder().encodeToString(byteArray.toByteArray());
        byteArray.close();
        Map<String, String> res = new HashMap<>(2);
        res.put("id",uuid);
        res.put("image",image);
        return FinalResult.ok().data(res);
    }
    public List<Long> getRolesById(Long id)
    {
        User user = this.baseMapper.selectById(id);
        if(!Objects.isNull(user))
        {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }
        assert false;
        return userRoleService.getRoleIdsByUserId(user.getId());
    }


//    This classes are the utils


    private Page<UserVo> toUserVOPage(Page<User> userPage)
    {
        Page<UserVo> pageInfo = new Page<>();
        BeanUtils.copyProperties(userPage, pageInfo);
        List<UserVo> userVos = toUserVoList(userPage.getRecords());
        pageInfo.setRecords(userVos);
        return pageInfo;
    }

    private List<UserVo> toUserVoList( List<User> users)
    {
        if (users.isEmpty())
        {
            return Collections.emptyList();
        }
        List<UserVo> userVos = new ArrayList<>();
        List<Long> deptIds = users.stream().map(User::getDepartmentId).collect(Collectors.toList());
        List<String> deptNames = departmentService.getDeptNamesByIds(deptIds);
        AtomicInteger index = new AtomicInteger();
        users.forEach(user ->
        {
            UserVo userVo = UserUtil.executeVo(user, deptNames.get(index.getAndIncrement()));
            userVos.add(userVo);
        });
        return userVos;
    }

    private static LambdaQueryWrapper<User> executeWrapper( UserForm userForm)
    {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (null != userForm.getDepartmentId())
        {
            wrapper.eq(User::getDepartmentId, userForm.getDepartmentId());
        }
        if (userForm.getUsername()!=null)
        {
            wrapper.eq(User::getUsername, userForm.getUsername());
        }
        if (userForm.getEmail()!=null)
        {
            wrapper.eq(User::getEmail, userForm.getEmail());
        }
        if (userForm.getSex()!=null)
        {
            wrapper.eq(User::getSex, userForm.getSex());
        }
        if (userForm.getNikeName()!=null)
        {
            wrapper.eq(User::getNickname, userForm.getNikeName());
        }
        if (userForm.getForbidden() != null)
        {
            wrapper.eq(User::getForbidden, userForm.getForbidden());
        }
        return wrapper;
    }
    public List<UserVo> getUserList(UserForm userForm)
    {
        LambdaQueryWrapper<User> wrapper = executeWrapper(userForm);
        List<User> users = this.baseMapper.selectList(wrapper);
        return toUserVoList(users);
    }
}