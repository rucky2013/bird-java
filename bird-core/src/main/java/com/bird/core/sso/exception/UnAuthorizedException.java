package com.bird.core.sso.exception;

import com.bird.core.HttpCode;
import com.bird.core.exception.AbstractException;

/**
 * 定义用户身份过期异常类
 */
public class UnAuthorizedException extends AbstractException {
    public UnAuthorizedException(String message) {
        super(message);
    }

    @Override
    protected HttpCode getHttpCode() {
        return HttpCode.UNAUTHORIZED;
    }
}
