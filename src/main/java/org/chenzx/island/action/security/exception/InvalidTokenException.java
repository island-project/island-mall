package org.chenzx.island.action.security.exception;

import org.springframework.security.access.AccessDeniedException;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description token 无效异常
 * @date 2022/7/22 10:34
 */
public class InvalidTokenException extends AccessDeniedException {

    public InvalidTokenException(String msg) {
        super(msg);
    }
}
