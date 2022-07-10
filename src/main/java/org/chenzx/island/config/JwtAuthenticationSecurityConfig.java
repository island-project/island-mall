package org.chenzx.island.config;

import org.chenzx.island.filter.security.JwtAuthenticationFilter;
import org.chenzx.island.handler.security.LoginAuthenticationFailureHandler;
import org.chenzx.island.handler.security.LoginAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 登录认证过滤器配置
 * @date 2022/7/10 10:51
 */
@Configuration
public class JwtAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    /**
     * userDetailsService
     */
    @Autowired
    @Qualifier("jwtTokenUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * 登录成功处理器
     */
    @Autowired
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;

    /**
     * 登录失败处理器
     */
    @Autowired
    private LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;

    /**
     * 加密
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        filter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        //认证成功处理器
        filter.setAuthenticationSuccessHandler(loginAuthenticationSuccessHandler);
        //认证失败处理器
        filter.setAuthenticationFailureHandler(loginAuthenticationFailureHandler);
        //直接使用DaoAuthenticationProvider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //设置userDetailService
        provider.setUserDetailsService(userDetailsService);
        //设置加密算法
        provider.setPasswordEncoder(passwordEncoder);
        builder.authenticationProvider(provider);
        //将这个过滤器添加到UsernamePasswordAuthenticationFilter之前执行
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

}
