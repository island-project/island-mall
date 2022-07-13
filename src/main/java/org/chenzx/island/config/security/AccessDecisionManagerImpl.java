package org.chenzx.island.config.security;

import cn.hutool.core.exceptions.ValidateException;
import lombok.RequiredArgsConstructor;
import org.chenzx.island.enums.SystemEnum;
import org.chenzx.island.exception.NoTokenException;
import org.chenzx.island.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 对用户是否具有访问该权限进行判断的实现类
 * @date 2022/7/13 20:34
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccessDecisionManagerImpl implements AccessDecisionManager {

    private final JwtUtils jwtUtils;

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute ca : configAttributes) {
            // 获取访问该路径需要的权限
            String attribute = ca.getAttribute();
            if (FilterInvocationSecurityMetadataSourceImpl.NO_PERMISSION.equals(attribute)) {
                return;
            }
            FilterInvocation filterInvocation = (FilterInvocation) object;
            String token = filterInvocation.getHttpRequest().getHeader(SystemEnum.TOKEN_HEADER.getValue());
            try {
                jwtUtils.checkTokenException(token);
            } catch (ValidateException ex) {
                throw new InsufficientAuthenticationException("token无效或token过期");
            } catch (NoTokenException ex) {
                throw new InsufficientAuthenticationException("需要登录");
            }

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(attribute)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("无权限访问");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
