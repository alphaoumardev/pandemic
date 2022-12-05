package com.alpha.pandemic.structor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EndPointController
{
    String operation() default "";
    String systemMessage() default "A system exception has occured";
}
