package org.chenzx.island.action.security.config;

import lombok.RequiredArgsConstructor;
import org.chenzx.island.action.security.business.AccessDecisionManagerImpl;
import org.chenzx.island.action.security.business.FilterInvocationSecurityMetadataSourceImpl;
import org.chenzx.island.action.security.filter.TokenAuthenticationFilter;
import org.chenzx.island.action.security.handler.LoginAuthenticationEntryPoint;
import org.chenzx.island.action.security.handler.RequestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
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
    private final FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;
    private final AccessDecisionManagerImpl accessDecisionManager;

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
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    // 设置鉴权过滤器,一个用来获取访问该路径需要的权限,另一个用来检查用户是否具有该权限
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
                        object.setAccessDecisionManager(accessDecisionManager);
                        return object;
                    }
                })
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
