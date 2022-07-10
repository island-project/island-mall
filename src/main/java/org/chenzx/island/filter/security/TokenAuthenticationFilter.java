package org.chenzx.island.filter.security;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chenzx.island.enums.SystemEnum;
import org.chenzx.island.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description Token校验过滤器
 * @date 2022/7/10 10:28
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(SystemEnum.TOKEN_HEADER.getValue());
        if (StrUtil.isNotEmpty(token) && jwtUtils.checkToken(token)) {
            // token校验成功,应该放入ThreadLocal中,方便后续校验
            log.info("token校验成功! token:{}", token);
        }
        filterChain.doFilter(request, response);
    }
}
