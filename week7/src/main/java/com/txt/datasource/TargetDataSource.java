package com.txt.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description :
 * @Date : 2022-02-27
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {
    EnumDataSourceType value() default EnumDataSourceType.WRITE;
}
