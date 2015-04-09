/**
 * 
 */
package com.cqeca.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/***
* @ClassName: FilterCheckUrl 
* @Description: filter过滤url验证
* @author chenrui
* @date 2015-4-8 下午4:17:00
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FilterCheckUrl {

	// 是否需要参数验证，true需要 false不需要
	boolean value() default true;    
}
