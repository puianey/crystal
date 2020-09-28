package com.puianey.crystal.common.constant;

import lombok.Getter;

/**
 * @Author: puianey
 * @Date: 2018/9/14 20:52
 */
public interface Constants {

    /**
     * 日志调用字符串
     */
    String TRACE_KEY = "traceKey";

    /**
     * 日志MDC
     */
    String[] DEFAULT_TRACE_VALUE = {"X-B3-TraceId", "X-B3-SpanId"};

    /**
     * BigDecimal默认格式化方式
     */
    String DECIMAL_FORMAT = "0.00";

    /**
     * LocalDate默认格式
     */
    String LOCAL_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * LocalTime默认格式
     */
    String LOCAL_TIME_FORMAT = "HH:mm:ss";

    /**
     * LocalDateTime默认格式
     */
    String LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Base scan
     */
    String BASE_SCAN = "com.puianey.crystal.tools.base.holder";

    /**
     * 线程池名称前缀
     */
    String THREAD_POOL_PREFIX = "Thread-pool-";

    /**
     * 默认拦截器的拦截地址
     */
    String INTERCEPTOR_PATH = "/**";

    /**
     * 默认成功提示
     */
    String BASE_SUCCESS_MSG = "操作成功";

    /**
     * 默认错误提示
     */
    String BASE_ERROR_MSG = "开小差中。。。。";

    /**
     * 远程配置中心的默认根路径
     */
    String REMOTE_CONFIG_CENTER_ROOT = "config";

    /**
     * 远程配置中心的默认上下文名称
     */
    String REMOTE_CONFIG_CENTER_CONTEXT = "application";

    /**
     * 自定义header
     */
    @Getter
    enum HEADER {

        API_VERSION("api-version"),

        DEVICE_CODE("device-code"),

        DEVICE_TYPE("device-type"),

        CHANNEL_CODE("channel-code"),

        TOKEN("token");

        private final String name;

        HEADER(String name) {
            this.name = name;
        }
    }

    interface ThreadPoolConfig {

        Integer corePoolSize = 5;

        Integer maxPoolSize = 10;

        Integer queueCapacity = 20;

        Integer keepAliveSeconds = 200;
    }

}
