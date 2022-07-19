package org.chenzx.island.action.security.handler;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.chenzx.island.action.security.config.SecurityConfigurationProperties;
import org.chenzx.island.action.security.pojo.SysUser;
import org.chenzx.island.common.utils.JwtUtils;
import org.chenzx.island.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
    private final RedisTemplate<String, Object> redisTemplate;
    @Value("${jwt.access-token-expiration-time}")
    private Integer accessTokenExpirationTime;
    @Value("${jwt.refresh-token-expiration-time}")
    private Integer refreshTokenExpirationTime;
    private final SecurityConfigurationProperties securityConfigurationProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        Map<String, Object> map = BeanUtil.beanToMap(sysUser);
        map.remove("password");
        // 构造token
        String accessToken = jwtUtils.getJwtToken(map, accessTokenExpirationTime);
        HashMap<String, String> refreshTokenMap = Maps.newHashMap();
        refreshTokenMap.put("username", (String) map.get("username"));
        String refreshToken = jwtUtils.getJwtToken(refreshTokenMap, refreshTokenExpirationTime);
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
        /**
         * 用户访问时使用的token
         */
        private String accessToken;
        /**
         * 刷新token
         */
        private String refreshToken;
    }
}
