package com.alpha.pandemic.structor.handler;

import com.alpha.pandemic.structor.tools.JsonWriterUtil;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailure implements AuthenticationFailureHandler
{
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        FinalResult result;
        if (e instanceof AccountExpiredException)
        {
            //账号过期
            result = FinalResult.error(ResultCode.USER_ACCOUNT_EXPIRED);
        }
        else if (e instanceof BadCredentialsException)
        {
            //密码错误
            result = FinalResult.error(ResultCode.USER_CREDENTIALS_ERROR);
        }
        else if (e instanceof CredentialsExpiredException)
        {
            //密码过期
            result = FinalResult.error(ResultCode.USER_CREDENTIALS_EXPIRED);
        }
        else if (e instanceof DisabledException)
        {
            //账号不可用
            result = FinalResult.error(ResultCode.USER_ACCOUNT_DISABLE);
        }
        else if (e instanceof LockedException)
        {
            //账号锁定
            result = FinalResult.error(ResultCode.USER_ACCOUNT_LOCKED);
        }
        else if (e instanceof InternalAuthenticationServiceException)
        {
            //用户不存在
            result = FinalResult.error(e.getMessage());
        }
        else
        {
            //其他错误
            result = FinalResult.error(e.getMessage());
        }
        JsonWriterUtil.buildJsonResult(response, result);
    }
}
