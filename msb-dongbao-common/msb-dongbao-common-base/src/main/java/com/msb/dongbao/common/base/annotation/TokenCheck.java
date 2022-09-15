package com.msb.dongbao.common.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验token的注解
 *
 * @author xcy
 * @date 2022/9/15 - 9:08
 */
//这种枚举类型的常量提供了注释可能出现在 Java 程序中的句法位置的简单分类
@Target(ElementType.METHOD)
//注解保留策略
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenCheck {
	/**
	 * 是否校验token
	 * @return true表示校验token，false表示不校验token
	 */
	boolean required() default true;
}
