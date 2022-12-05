package com.alpha.pandemic.structor.security;

import com.alpha.pandemic.mappers.RoleMapper;
import com.alpha.pandemic.mappers.UserMapper;
import com.alpha.pandemic.mappers.UserRoleMapper;
import com.alpha.pandemic.models.Menu;
import com.alpha.pandemic.models.Role;
import com.alpha.pandemic.models.User;
import com.alpha.pandemic.structor.tools.JsonWriterUtil;
import com.alpha.pandemic.utils.constant.Constant;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("userDetailsServices")
@AllArgsConstructor
public class UserDetailsLogin implements UserDetailsService
{
    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String s)
    {
        User user = userMapper.getByUsername(s);
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        assert response != null;
        if(Objects.isNull(user))
        {
            JsonWriterUtil.buildJsonResult(response, FinalResult.error(ResultCode.USER_ACCOUNT_NOT_EXIST));
            return null;
        }

        assert false;
        List<Long> roleIds= userRoleMapper.getRoleIdsByUserId(user.getId());
        if(roleIds.isEmpty())
        {
            JsonWriterUtil.buildJsonResult(response, FinalResult.error(ResultCode.DONT_HAVE_ANY_ROLE));
            return null;
        }
        List<Role> roles = roleMapper.selectBatchIds(roleIds);
        List<String> codes = roles.stream().map(Role::getRoleCode).collect(Collectors.toList());
        if(!codes.contains("ADMIN"))
        {
            List<Integer> flags = roles.stream().map(Role::getForbidden).collect(Collectors.toList());
            if(!flags.contains(Constant.ACTIVE))
            {
                JsonWriterUtil.buildJsonResult(response, FinalResult.error(ResultCode.ROLE_ALL_IS_FORBIDDEN));
                return null;
            }
        }
        LoginUser loginUser =new LoginUser();
        BeanUtils.copyProperties(user, loginUser);

        List<String> codeList = userMapper.getAuthListByUsername(user.getUsername(), Constant.ADMIN.equals(user.getType()));
        List<Menu> menus = userMapper.getMenusByUsername(Integer.parseInt(Constant.MENU), user.getUsername());

        loginUser.setAuthorities(codeList);
        loginUser.setMenus(menus);

        return loginUser;
    }
}
