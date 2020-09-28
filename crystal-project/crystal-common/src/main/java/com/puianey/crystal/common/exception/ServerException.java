package com.puianey.crystal.common.exception;

/**
 * 服务器异常（500）
 *
 * @Author: puianey
 * @Date: 2018/9/7 18:03
 */
public class ServerException extends RuntimeException {

    public ServerException() {
        super();
    }

    public ServerException(String message) {
        super(message);
    }
}
