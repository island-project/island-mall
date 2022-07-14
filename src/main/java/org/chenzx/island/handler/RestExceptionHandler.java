package org.chenzx.island.handler;

import cn.hutool.core.exceptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.chenzx.island.vo.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.chenzx.island.enums.SysResponseCodeEnum.*;

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
     * jwt 校验失败异常处理器
     *
     * @param ex 异常对象
     * @return 封装后的返回前端的异常信息
     */
    @ExceptionHandler({ValidateException.class})
    public Result jwtTokenExceptionHandler(ValidateException ex) {
        return Result.error(SECURITY_TOKEN_INVALID.getCode(), SECURITY_TOKEN_INVALID.getMsg());
    }

    @ExceptionHandler({Exception.class})
    public Result exceptionHandler(Exception e) {
        return Result.error(ERROR.getCode(), "内部服务器错误,请联系管理员");
    }

}
