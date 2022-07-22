package org.chenzx.island.action.security.handler;

import com.alibaba.fastjson.JSONObject;
import org.chenzx.island.common.pojo.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.chenzx.island.action.security.enums.SecurityEnum.SECURITY_OTHER_ERRORS;
import static org.chenzx.island.action.security.enums.SecurityEnum.SECURITY_USERNAME_PASSWORD_ERROR;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description Spring Security 登录失败处理器
 * @date 2022/7/10 9:50
 */
@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Result result;
        if (exception instanceof BadCredentialsException) {
            result = Result.error(SECURITY_USERNAME_PASSWORD_ERROR.getCode(), SECURITY_USERNAME_PASSWORD_ERROR.getMsg());
        } else {
            result = Result.error(SECURITY_OTHER_ERRORS.getCode(), exception.getLocalizedMessage());
        }
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(result));
        writer.flush();
        writer.close();
    }
}
