package org.chenzx.island.handler.security;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.chenzx.island.utils.JwtUtils;
import org.chenzx.island.vo.Result;
import org.chenzx.island.vo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description Spring Security 登录成功处理器
 * @date 2022/7/9 12:29
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtils jwtUtils;
    @Value("${jwt.access-token-expiration-time}")
    private Integer accessTokenExpirationTime;
    @Value("${jwt.refresh-token-expiration-time}")
    private Integer refreshTokenExpirationTime;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        Map<String, Object> map = BeanUtil.beanToMap(sysUser);
        map.remove("password");
        String accessToken = jwtUtils.getJwtToken(map, accessTokenExpirationTime);
        String refreshToken = jwtUtils.getJwtToken(map.get("id"), refreshTokenExpirationTime);
        Result result = Result.isOk(Token.builder().accessToken(accessToken).refreshToken(refreshToken).build());
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(result));
        writer.flush();
        writer.close();
    }

    @Data
    @Builder
    public static class Token {
        private String accessToken;
        private String refreshToken;
    }
}
