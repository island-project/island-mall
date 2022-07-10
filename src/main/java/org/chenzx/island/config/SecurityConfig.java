package org.chenzx.island.config;

import lombok.RequiredArgsConstructor;
import org.chenzx.island.filter.security.TokenAuthenticationFilter;
import org.chenzx.island.handler.security.LoginAuthenticationEntryPoint;
import org.chenzx.island.handler.security.RequestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description Spring Security 配置
 * @date 2022/7/10 11:11
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;
    private final LoginAuthenticationEntryPoint loginAuthenticationEntryPoint;
    private final RequestAccessDeniedHandler requestAccessDeniedHandler;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //禁用表单登录，前后端分离用不上
                .disable()
                //应用登录过滤器的配置，配置分离
                .apply(jwtAuthenticationSecurityConfig)

                .and()
                // 设置URL的授权
                .authorizeRequests()
                // 这里需要将登录页面放行,permitAll()表示不再拦截，/login 登录的url，/refreshToken刷新token的url
                //TODO 此处正常项目中放行的url还有很多，比如swagger相关的url，druid的后台url，一些静态资源
                .antMatchers("/sys/login", "/refreshToken")
                .permitAll()
                //hasRole()表示需要指定的角色才能访问资源
                // anyRequest() 所有请求   authenticated() 必须被认证
                .anyRequest()
                .authenticated()

                //处理异常情况：认证失败和权限不足
                .and()
                .exceptionHandling()
                //认证未通过，不允许访问异常处理器
                .authenticationEntryPoint(loginAuthenticationEntryPoint)
                //认证通过，但是没权限处理器
                .accessDeniedHandler(requestAccessDeniedHandler)

                .and()
                //禁用session，JWT校验不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                //将TOKEN校验过滤器配置到过滤器链中，否则不生效，放到UsernamePasswordAuthenticationFilter之前
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // 关闭csrf
                .csrf().disable();
    }


}
