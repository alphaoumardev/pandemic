package com.alpha.pandemic.structor.aspect;

import cn.hutool.json.JSONUtil;
import com.alpha.pandemic.models.Logs;
import com.alpha.pandemic.services.LogService;
import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.structor.security.LoginUser;
import com.alpha.pandemic.structor.tools.AddressUtil;
import com.alpha.pandemic.structor.tools.IpUtil;
import com.alpha.pandemic.structor.tools.UserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

@Aspect
@Slf4j
@Component
@AllArgsConstructor
public class EndPointAspect extends AspectSupport
{
    private final LogService logService;
    private final Logs sysLog = new Logs();

    @Pointcut("@annotation(com.alpha.pandemic.structor.annotation.EndPointController)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object saveSysLog(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object result;
        // 开始时间
        long startTime = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        EndPointController controllerEndpoint = method.getAnnotation(EndPointController.class);
        if (controllerEndpoint != null)
        {
            String operation = controllerEndpoint.operation();
            sysLog.setOperation(operation);
        }

        // 请求的参数
        Object[] args = joinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        sysLog.setParams("paramName:" + Arrays.toString(paramNames) + ",args:" + Arrays.toString(args));

        // 请求的IP
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String ipAddr = IpUtil.getIpAddr(request);
        sysLog.setIp(ipAddr);

        // 地理位置
        sysLog.setLocation(AddressUtil.getCityInfo(ipAddr));
        // 操作人
        LoginUser loginUser = UserUtil.getCurrentLoginUser();
        if (Objects.isNull(loginUser))
        {
            sysLog.setUsername("[SYSTEM]匿名用户");
        }
        else
        {
            sysLog.setUsername(loginUser.getUsername());
        }

        //执行目标方法
        result = joinPoint.proceed();

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求结果
        String resultStr = "response:" + postHandle(result);
        sysLog.setResult(resultStr);

        //执行耗时
        sysLog.setTime(BigDecimal.valueOf(System.currentTimeMillis() - startTime));

        logService.saveLog(sysLog);
        return result;
    }

    private String postHandle(Object retVal)
    {
        if (Objects.isNull(retVal))
        {
            return "{}";
        }
        return JSONUtil.toJsonStr(retVal);
    }
}
