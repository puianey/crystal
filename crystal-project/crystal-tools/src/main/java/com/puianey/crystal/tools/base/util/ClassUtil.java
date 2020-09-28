package com.puianey.crystal.tools.base.util;

import lombok.experimental.UtilityClass;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author: puianey
 * @Date: 2019-07-17 20:08
 * @Description:
 */
@UtilityClass
public class ClassUtil extends ClassUtils {

	private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

	/**
	 * 获取方法参数信息
	 *
	 * @param constructor    构造器
	 * @param parameterIndex 参数序号
	 * @return {MethodParameter}
	 */
	public MethodParameter getMethodParameter(Constructor<?> constructor, int parameterIndex) {
		var methodParameter = new SynthesizingMethodParameter(constructor, parameterIndex);
		methodParameter.initParameterNameDiscovery(parameterNameDiscoverer);
		return methodParameter;
	}

	/**
	 * 获取方法参数信息
	 *
	 * @param method         方法
	 * @param parameterIndex 参数序号
	 * @return {MethodParameter}
	 */
	public MethodParameter getMethodParameter(Method method, int parameterIndex) {
		var methodParameter = new SynthesizingMethodParameter(method, parameterIndex);
		methodParameter.initParameterNameDiscovery(parameterNameDiscoverer);
		return methodParameter;
	}

	/**
	 * 获取Annotation
	 *
	 * @param method         方法
	 * @param annotationType 注解类
	 * @param <A>            泛型标记
	 * @return {Annotation}
	 */
	@Nullable
	public <A extends Annotation> A getAnnotation(Method method, Class<A> annotationType) {
		var targetClass = method.getDeclaringClass();
		var specificMethod = ClassUtil.getMostSpecificMethod(method, targetClass);
		specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
		A annotation = AnnotatedElementUtils.findMergedAnnotation(specificMethod, annotationType);
		if (Objects.nonNull(annotation)) {
			return annotation;
		}
		// 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
		return AnnotatedElementUtils.findMergedAnnotation(specificMethod.getDeclaringClass(), annotationType);
	}

}
