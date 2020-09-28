package com.puianey.crystal.autoconfigure.base;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: puianey
 * @Date: 2019-07-04 17:38
 * @Description:
 */
class BaseImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{};
//        return new String[]{"com.yoho8.frameboot.autoconfigure.web.GlobalInterceptor"};
    }

}
