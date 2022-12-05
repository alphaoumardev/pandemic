package com.alpha.pandemic.structor.filter;

import cn.hutool.core.util.StrUtil;
import com.alpha.pandemic.structor.security.LoginUser;
import com.alpha.pandemic.structor.tools.*;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthoraztionFilter extends OncePerRequestFilter
{
    @Value("${token.head}")
    private String head;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthoraztionFilter(JwtUtil jwtUtil, @Qualifier("userDetailsServices") UserDetailsService userDetailsService)
    {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void doFilterInternal( HttpServletRequest request,  HttpServletResponse response,  FilterChain filterChain) throws ServletException, IOException
    {
        String token = RequestUtil.getHeadToken(request,head);
        if (!StrUtil.isEmpty(token))
        {
            if(!jwtUtil.isJwtStr(token))
            {
                JsonWriterUtil.buildJsonResult(response, FinalResult.error(ResultCode.JWT_FORMAT_ERROR));
                return;
            }
            if (jwtUtil.isExpired(token) || Objects.isNull(jwtUtil.getClaimByToken(token)))
            {
                JsonWriterUtil.buildJsonResult(response, FinalResult.error(ResultCode.JWT_IS_EXPIRED));
                return;
            }

            LoginUser loginUser = UserUtil.getCurrentLoginUser();
            if (Objects.isNull(loginUser))
            {
                loginUser = (LoginUser) userDetailsService.loadUserByUsername(jwtUtil.getAccountName(token));
                LoginUserUtil.setSecurityLoginUser(request, loginUser);
            }
        }
        filterChain.doFilter(request, response);
    }
}
