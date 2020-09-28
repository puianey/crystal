package com.puianey.crystal.autoconfigure.base;

import com.puianey.crystal.common.constant.Constants;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * @Author: puianey
 * @Date: 2019-07-05 14:13
 * @Description:
 */
public class BaseScanRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		var scanner = new BaseClassPathBeanDefinitionScanner(registry);
		scanner.doScan(Constants.BASE_SCAN);
	}

	static class BaseClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

		BaseClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
			super(registry);
		}

		@Override
		protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
			return super.doScan(basePackages);
		}
	}

}
