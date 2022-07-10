package org.chenzx.island.handler.security;

import com.alibaba.fastjson.JSONObject;
import org.chenzx.island.vo.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.chenzx.island.enums.SysResponseCodeEnum.SECURITY_UNAUTHORIZED_ACCESS;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 用户无权限访问时处理器
 * @date 2022/7/10 10:12
 */
@Component
public class RequestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result result = Result.error(SECURITY_UNAUTHORIZED_ACCESS.getCode(), SECURITY_UNAUTHORIZED_ACCESS.getMsg());
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(result));
        writer.flush();
        writer.close();
    }
}
