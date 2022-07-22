package org.chenzx.island.action.security.exception;

import org.springframework.security.authentication.InsufficientAuthenticationException;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description token过期时抛出该异常
 * @date 2022/7/21 22:15
 */
public class TokenExpiredException extends InsufficientAuthenticationException {

    public TokenExpiredException(String msg) {
        super(msg);
    }
}
