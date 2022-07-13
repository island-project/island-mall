package org.chenzx.island.exception;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 需要token时没有token产生的异常
 * @date 2022/7/13 21:28
 */
public class NoTokenException extends RuntimeException {

    public NoTokenException(String message) {
        super(message);
    }
}
