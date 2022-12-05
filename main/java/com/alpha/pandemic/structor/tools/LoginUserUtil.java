package com.alpha.pandemic.structor.tools;

import com.alpha.pandemic.structor.security.LoginUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@SuppressWarnings("unused")
public class LoginUserUtil
{
    public static void addLoginUserExpireTime(long sec)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!Objects.isNull(authentication))
        {
            if (authentication instanceof UsernamePasswordAuthenticationToken)
            {
                LoginUser loginUser = (LoginUser) authentication.getPrincipal();
                loginUser.setExpireTime(sec + loginUser.getExpireTime());
                UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);
            }
        }
    }

    public static void setSecurityLoginUser(HttpServletRequest request, LoginUser loginUser)
    {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
