package com.alpha.pandemic.structor.filter;

import cn.hutool.core.util.StrUtil;
import com.alpha.pandemic.structor.security.LoginUser;
import com.alpha.pandemic.structor.tools.RequestUtil;
import com.alpha.pandemic.structor.tools.UserUtil;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.ResultCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

//@Deprecated
public class LoginSessionFilter implements Filter
 {

    @Value("${token.head}")
    private String head;

     @Override
     public void init(FilterConfig filterConfig) throws ServletException {}

     @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // Get the token request
        String token = RequestUtil.getHeadToken((HttpServletRequest) servletRequest, head);
        if (!StrUtil.isEmpty(token))
        {
            LoginUser loginUser = UserUtil.getCurrentLoginUser();
            if (!Objects.isNull(loginUser)){
                long now = System.currentTimeMillis();
                if (now > loginUser.getExpireTime())
                {
                    throw new BusinessException(ResultCode.USER_SESSION_INVALID);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
 }
