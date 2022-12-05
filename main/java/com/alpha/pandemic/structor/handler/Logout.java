package com.alpha.pandemic.structor.handler;

import com.alpha.pandemic.structor.tools.RequestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Deprecated
@Component
public class Logout implements LogoutHandler
{
    @Value("${token.head}")
    private String head;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
        String token = RequestUtil.getHeadToken(request, head);
        if (!token.isEmpty())
        {
            SecurityContextHolder.clearContext();
        }
    }
}
