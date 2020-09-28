package com.puianey.crystal.tools.json.format;

import com.puianey.crystal.common.constant.Constants;

import java.lang.annotation.*;

/**
 * @Author: puianey
 * @Date: 2018/9/18 16:46
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BigDecimalFormat {

    /**
     * 格式化类型
     * <p>
     * ***  格式化之前的数字： 1234.56789             ***
     * ***  采用style: 0.0  --->> 1234.6            ***
     * ***  采用style: 00000.000  --->> 01234.568   ***
     * ***  采用style: ##000.000  --->> 1234.568    ***
     * ***  采用style: -000.000  --->> -1234.568    ***
     * ***  采用style: -0,000.0#  --->> -1,234.57   ***
     * ***  采用style: 0.00E000  --->> 1.23E003     ***
     * ***  采用style: 0.00%  --->> 123456.79%      ***
     * ***  采用style: 0.00‰  --->> 1234567.89‰     ***
     */
    String value() default Constants.DECIMAL_FORMAT;

}
