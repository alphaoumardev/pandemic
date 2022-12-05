package com.alpha.pandemic.utils.exception;

import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalException
{
    @ExceptionHandler(Exception.class)
    public FinalResult error(Exception e)
    {
        e.printStackTrace(); // this is the
        return FinalResult.error(ResultCode.ARITHMETIC);
    }

    @ExceptionHandler(ArithmeticException.class)
    public FinalResult error(HttpServletRequest request, ArithmeticException e)
    {
        log.error("Global Exception [ArithmeticException]处理: [api: {}], 原因: {}", request.getRequestURL(), e.getMessage());
        return FinalResult.error(ResultCode.ARITHMETIC);
    }

    @ExceptionHandler(BusinessException.class)
    public FinalResult error(HttpServletRequest request, BusinessException e)
    {
        log.error("全局异常[BusinessException]处理: [api: {}], 原因: {}", request.getRequestURL(), e.getMessage());
        return FinalResult.error().code(e.getCode()).message(e.getMessage());
    }
}
