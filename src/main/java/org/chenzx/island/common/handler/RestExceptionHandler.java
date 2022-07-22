package org.chenzx.island.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.chenzx.island.action.security.enums.SecurityEnum;
import org.chenzx.island.action.security.exception.RefreshTokenExpiredException;
import org.chenzx.island.common.pojo.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.chenzx.island.common.enums.SysResponseCodeEnum.ERROR;
import static org.chenzx.island.common.enums.SysResponseCodeEnum.REQUEST_PARAMETER_EXCEPTION;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 全局异常处理器
 * @date 2022/7/14 13:42
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * 请求参数异常处理器
     *
     * @param e 异常对象
     * @return 封装后的返回前端的异常信息
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result paramExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return Result.error(REQUEST_PARAMETER_EXCEPTION.getCode(), fieldError.getDefaultMessage());
            }
        }
        return Result.error(REQUEST_PARAMETER_EXCEPTION.getCode(), "请求参数错误");
    }

    /**
     * 刷新令牌过期异常处理器
     *
     * @param e 异常对象
     * @return 封装后的返回前端的异常信息
     */
    @ExceptionHandler({RefreshTokenExpiredException.class})
    public Result refreshTokenExpiredExceptionHandler(RefreshTokenExpiredException e) {
        return Result.error(SecurityEnum.SECURITY_REFRESH_TOKEN_EXPIRED.getCode(), SecurityEnum.SECURITY_REFRESH_TOKEN_EXPIRED.getMsg());
    }

    /**
     * 通用异常处理器,处理断言失败的异常和其他未被定义的异常
     *
     * @param e 异常对象
     * @return 封装后的返回前端的异常信息
     */
    @ExceptionHandler({IllegalArgumentException.class, Exception.class})
    public Result currencyExceptionHandle(Exception e) {
        log.error(e.getMessage());
        return Result.error(ERROR.getCode(), e.getLocalizedMessage());
    }

}
