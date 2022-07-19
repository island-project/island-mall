package org.chenzx.island.action.security.handler;

import com.alibaba.fastjson.JSONObject;
import org.chenzx.island.common.vo.Result;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.chenzx.island.common.enums.SysResponseCodeEnum.SECURITY_LOGIN_REQUIRED;
import static org.chenzx.island.common.enums.SysResponseCodeEnum.SECURITY_TOKEN_INVALID;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 当用户未通过认证访问受保护资源时
 * @date 2022/7/10 10:09
 */
@Component
public class LoginAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result;
        if (authException instanceof InsufficientAuthenticationException) {
            result = Result.error(SECURITY_TOKEN_INVALID.getCode(), SECURITY_TOKEN_INVALID.getMsg());
        } else {
            result = Result.error(SECURITY_LOGIN_REQUIRED.getCode(), SECURITY_LOGIN_REQUIRED.getMsg());
        }
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(result));
        writer.flush();
        writer.close();
    }
}
