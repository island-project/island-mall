package org.chenzx.island.exception;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 业务异常, 该异常的处理逻辑为直接将异常内的异常信息返回
 * @date 2022/7/10 17:19
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
