package com.alpha.pandemic.structor.filter;

import com.alpha.pandemic.beans.vo.LoginVo;
import com.alpha.pandemic.config.RedisCustom;
import com.alpha.pandemic.services.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

public class SecurityLoginFilter extends UsernamePasswordAuthenticationFilter
{
    private final RedisService redisService;

    public SecurityLoginFilter(RedisService redisService)
    {
        this.redisService = redisService;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user/login", "POST"));
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication( HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        if (!request.getMethod().equals("POST"))
        {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = request.getInputStream();
        LoginVo loginBody = objectMapper.readValue(inputStream, LoginVo.class);

        if (loginBody.getUsername().isEmpty() || loginBody.getPassword().isEmpty())
        {
            throw new InternalAuthenticationServiceException("The username and the password can't be empty！");
        }

        if (loginBody.getVerifyCode().isEmpty())
        {
            throw new AuthenticationServiceException("Please input the password！");
        }
        // The verification code
        Long interval = redisService.getKeyInterval(RedisCustom.LOGIN_VERIFY_CODE + loginBody.getVerifyCodeId());
        if (interval==null || interval <= 3)
        {
            throw new AuthenticationServiceException("The tokennnnnnnnnnn has expired ！");
        }

        String code = redisService.getLoginVerifyCode(loginBody.getVerifyCodeId());
        if (!loginBody.getVerifyCode().equalsIgnoreCase(code))
        {
            throw new AuthenticationServiceException("The token is incorrect！");
        }
        redisService.deleteLoginVerifyCode(loginBody.getVerifyCodeId());

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
