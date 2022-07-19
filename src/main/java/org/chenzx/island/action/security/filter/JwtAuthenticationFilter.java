package org.chenzx.island.action.security.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 普通登录认证过滤器
 * @date 2022/7/2 12:17
 * <br />原有的UsernamePasswordAuthenticationFilter只支持表单登录,不支持json格式登录
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final static String USERNAME_KEY = "username";
    private final static String PASSWORD_KEY = "password";

    public JwtAuthenticationFilter() {
        super(new AntPathRequestMatcher("/sys/auth/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        JSONObject requestJson = this.httpServletRequest2JsonObject(request);
        String username = requestJson.getString(USERNAME_KEY);
        String password = requestJson.getString(PASSWORD_KEY);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username.trim(), password);
        return getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * HttpServletRequest对象转换为JSONObject对象
     *
     * @param request 接收到的请求
     * @return JSONObject
     */
    private JSONObject httpServletRequest2JsonObject(HttpServletRequest request) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            String tempString;
            StringBuffer buffer = new StringBuffer();
            while ((tempString = bufferedReader.readLine()) != null) {
                buffer.append(tempString);
            }
            String requestBody = URLDecoder.decode(buffer.toString(), StandardCharsets.UTF_8);
            return JSONObject.parseObject(requestBody);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
