package com.alpha.pandemic.structor.handler;

import com.alpha.pandemic.beans.vo.UserVo;
import com.alpha.pandemic.mappers.RoleMapper;
import com.alpha.pandemic.models.User;
import com.alpha.pandemic.services.DepartmentService;
import com.alpha.pandemic.services.LogingLogService;
import com.alpha.pandemic.services.UserService;
import com.alpha.pandemic.structor.tools.JsonWriterUtil;
import com.alpha.pandemic.structor.tools.JwtUtil;
import com.alpha.pandemic.structor.tools.UserUtil;
import com.alpha.pandemic.utils.response.FinalResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class LoginSuccess implements AuthenticationSuccessHandler
{
    private final UserService userService;
    private final DepartmentService departmentService;
    private final JwtUtil jwtUtil;
    private final LogingLogService logingLogService;
    private final RoleMapper roleMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,  Authentication auth) throws IOException
    {
        User user = userService.getByUsername(auth.getName());

        String deptName = departmentService.getDepartmentNameById(user.getDepartmentId());
        Map<String, Object> loginData = new HashMap<>(2);

        // Getting the userRole
        List<String> names = roleMapper.queryRoleNamesByUserId(user.getId());

        UserVo userVo = UserUtil.executeVo(user, deptName);
        userVo.setRoles(String.join(",", names));
        loginData.put("data", userVo);

        // Creating a new token
        String token = jwtUtil.generateToken(user.getUsername());
        loginData.put("token", token);

        logingLogService.saveLoginLog(auth.getName(), request);
        JsonWriterUtil.buildJsonResult(response, FinalResult.ok().data(loginData));
    }
}
