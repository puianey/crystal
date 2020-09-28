package com.puianey.crystal.tools.json.format;

import com.puianey.crystal.common.constant.StringPool;
import com.puianey.crystal.tools.json.Sensitive;

import java.lang.annotation.*;

/**
 * @Author: puianey
 * @Date: 2018/9/18 16:46
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SensitiveFormat {

    /**
     * 脱敏开始位置
     *
     * @return int
     */
    int start() default 0;

    /**
     * 脱敏字符长度
     *
     * @return int
     */
    int length() default 0;

    /**
     * 从前往后开始脱敏
     * 从后往前开始脱敏
     *
     * @return SensitivePositionEnum
     */
    SensitivePositionEnum position() default SensitivePositionEnum.START;

    /**
     * placeholder
     *
     * @return placeholder
     */
    String placeholder() default StringPool.ASTERISK;

    /**
     * 自定义脱敏类，实现Sensitive接口
     *
     * @return class
     */
    Class<? extends Sensitive> value() default Sensitive.class;

    enum SensitivePositionEnum {

        /**
         * 从前往后
         */
        START,
        /**
         * 从后往前
         */
        END

    }

}
