package org.chenzx.island.action.security.business;

import lombok.RequiredArgsConstructor;
import org.chenzx.island.action.security.config.SecurityConfigurationProperties;
import org.chenzx.island.action.security.pojo.SysAuthDo;
import org.chenzx.island.action.security.service.ISysAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description Spring Security 判断当前访问路径所需要的权限
 * @date 2022/7/13 19:50
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    private final ISysAuthService sysAuthService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    private final SecurityConfigurationProperties securityProperties;
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    protected static final String NO_PERMISSION = "noPermission";

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        if (securityProperties.getExcludeUrl().contains(requestUrl)) {
            // 如果挡墙请求的路径在排除的url列表中,返回null,则不需要任何权限
            return null;
        }
        List<SysAuthDo> sysAuthList = getAllAuthMap();
        for (SysAuthDo auth : sysAuthList) {
            if (ANT_PATH_MATCHER.match(auth.getUrl(), requestUrl)) {
                return SecurityConfig.createList(auth.getName());
            }
        }

        return SecurityConfig.createList(NO_PERMISSION);
    }

    private List<SysAuthDo> getAllAuthMap() {
        return sysAuthService.queryAllSysAuth();
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
