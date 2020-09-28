package com.puianey.crystal.auto.annotation;

import com.puianey.crystal.auto.constant.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: puianey
 * @Date: 2018-12-12 19:59
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tag {

	Constants.AutoType[] value();

}
