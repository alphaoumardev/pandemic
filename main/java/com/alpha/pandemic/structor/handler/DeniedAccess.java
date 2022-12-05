package com.alpha.pandemic.structor.handler;

import com.alpha.pandemic.structor.tools.JsonWriterUtil;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Primary
@Component
public class DeniedAccess implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException
    {
        JsonWriterUtil.buildJsonResult(response, FinalResult.error(ResultCode.NOT_AUTH));
    }
}
