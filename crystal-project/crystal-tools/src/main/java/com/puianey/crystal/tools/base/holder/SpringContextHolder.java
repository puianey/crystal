package com.puianey.crystal.tools.base.holder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Author: puianey
 * @Date: 2018/9/14 18:44
 */
@Slf4j
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

	private static ApplicationContext applicationContext;

	private static final ConcurrentHashMap<Class<?>, Map<Class<?>, Object>> multiImplClassMap = new ConcurrentHashMap<>(16);

	private SpringContextHolder() {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Assert.notNull(applicationContext, "applicationContext must not be null");
		SpringContextHolder.applicationContext = applicationContext;
	}

	@Override
	public void destroy() {
		if (log.isDebugEnabled()) {
			log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
		}
		SpringContextHolder.applicationContext = null;
	}

	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
		return applicationContext.getBeansOfType(clazz);
	}

	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> clazz) {
		return applicationContext.getBeansWithAnnotation(clazz);
	}

	public static <T> T getOrderBeansOfType(Class<T> clazz) {

		return getBeansOfType(clazz).entrySet().stream().max(
			Comparator.comparing(
				entry -> -(Optional.ofNullable(entry.getValue().getClass().getAnnotation(Order.class))
					.map(Order::value)
					.orElse(Ordered.LOWEST_PRECEDENCE)
				)
			)
		).map(Map.Entry::getValue).orElse(null);

	}

	@SuppressWarnings("unchecked")
	public static <T> T getBeanForMultiImplClass(Class<T> clazz, Class type) {

		return (T) multiImplClassMap.computeIfAbsent(clazz,
			a -> getBeansOfType(a).entrySet().stream().collect(
				Collectors.toConcurrentMap(
					aa -> aa.getValue().getClass(),
					bb -> (Object) bb.getValue()
				)
			)).get(type);

	}

}
