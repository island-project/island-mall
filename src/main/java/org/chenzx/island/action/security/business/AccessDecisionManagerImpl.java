package org.chenzx.island.action.security.business;

import lombok.RequiredArgsConstructor;
import org.chenzx.island.common.enums.SystemEnum;
import org.chenzx.island.common.utils.JwtUtils;
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

    /**
     * 需要判断两个东西
     * 1. 用户的token是否过期,如果过期让前端尝试刷新token
     * 2. 用户是否有权限访问该接口
     *
     * @param authentication   the caller invoking the method (not null)
     * @param object           the secured object being called
     * @param configAttributes the configuration attributes associated with the secured
     *                         object being invoked
     * @throws AccessDeniedException               用户无权限访问该接口时抛出该异常
     * @throws InsufficientAuthenticationException 用户token过期时抛出该类的子类TokenExpiredException异常
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute ca : configAttributes) {
            // 获取访问该路径需要的权限
            String attribute = ca.getAttribute();
            if (FilterInvocationSecurityMetadataSourceImpl.NO_PERMISSION.equals(attribute)) {
                // 如果数据库中未配置权限,则默认允许任何角色(包括未登录)访问访问
                // 此处也一定要注意,需要授权的路径一定要在sys_auth表中配置
                return;
            }
            FilterInvocation filterInvocation = (FilterInvocation) object;
            jwtUtils.securityCheckToken(filterInvocation.getHttpRequest().getHeader(SystemEnum.TOKEN_HEADER.getValue()));

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(attribute)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException(null);
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
