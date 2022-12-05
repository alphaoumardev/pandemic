package com.alpha.pandemic.structor.aspect;

import org.apache.ibatis.binding.MapperMethod;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;


@SuppressWarnings("unused")
public abstract class AspectSupport
{
    Method resolveMethod(ProceedingJoinPoint point)
    {
        MapperMethod.MethodSignature signature = (MapperMethod.MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();

        Method method = getDeclaredMethod(targetClass, signature.getMapKey());
        if (method == null)
        {
            throw new IllegalStateException("Con't resolve : " + signature);
        }
        return method;
    }

    private Method getDeclaredMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try
        {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e)
        {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null)
            {
                return getDeclaredMethod(superClass, name, parameterTypes);
            }
        }
        return null;
    }
}
