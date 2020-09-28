package com.puianey.crystal.autoconfigure.base;

import com.puianey.crystal.auto.annotation.AutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: puianey
 * @Date: 2019-06-04 18:46
 * @Description:
 */
@AutoConfiguration
@Configuration
@Import({BaseImportSelector.class, BaseScanRegistrar.class})
public class BaseAutoConfiguration {

}
