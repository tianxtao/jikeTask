package com.txt.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description :
 * @Date : 2022-02-27
 */
@Component
@Aspect
@Slf4j
public class DynamicDataSourceAspect {
    @Before("@annotation(targetDataSource)")
    public void before(JoinPoint point, TargetDataSource targetDataSource) {
        try {
            TargetDataSource annotationOfClass = point.getTarget().getClass().getAnnotation(TargetDataSource.class);
            String methodName = point.getSignature().getName();
            Class[] parameterTypes = ((MethodSignature) point.getSignature()).getParameterTypes();
            Method method = point.getTarget().getClass().getMethod(methodName, parameterTypes);
            TargetDataSource methodAnnotation = method.getAnnotation(TargetDataSource.class);
            methodAnnotation = methodAnnotation == null ? annotationOfClass : methodAnnotation;
            EnumDataSourceType dataSourceType = methodAnnotation != null && methodAnnotation.value() != null ? methodAnnotation.value() : EnumDataSourceType.WRITE;
            DataSourceContextHolder.setDataSource(dataSourceType.name());
        } catch (NoSuchMethodException e) {
            log.warn("Aspect targetDataSource inspect exception.", e);
        }
    }

    @After("@annotation(targetDataSource)")
    public void after(JoinPoint point, TargetDataSource targetDataSource) {
        DataSourceContextHolder.clearDataSource();
    }
}
