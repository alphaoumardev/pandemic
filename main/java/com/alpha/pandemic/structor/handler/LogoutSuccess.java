package com.alpha.pandemic.structor.handler;

import com.alpha.pandemic.structor.tools.JsonWriterUtil;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Deprecated
@Component
public class LogoutSuccess implements LogoutSuccessHandler
{
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException
    {
        JsonWriterUtil.buildJsonResult(response, FinalResult.ok(ResultCode.LOGOUT_SUCCESS));
    }
}
