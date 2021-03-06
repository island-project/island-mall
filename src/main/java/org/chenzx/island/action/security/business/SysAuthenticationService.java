package org.chenzx.island.action.security.business;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.chenzx.island.action.security.config.SecurityConfigurationProperties;
import org.chenzx.island.action.security.exception.RefreshTokenExpiredException;
import org.chenzx.island.action.security.pojo.RefreshTokenVo;
import org.chenzx.island.action.security.pojo.RegisterFormVo;
import org.chenzx.island.action.security.pojo.SysUser;
import org.chenzx.island.action.security.pojo.SysUserDo;
import org.chenzx.island.action.security.service.ISysRoleService;
import org.chenzx.island.action.security.service.ISysUserService;
import org.chenzx.island.common.enums.SystemEnum;
import org.chenzx.island.common.exception.BusinessException;
import org.chenzx.island.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 系统认证相关 Service
 * @date 2022/7/10 17:07
 */
@Service
@Transactional(rollbackFor = {Exception.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysAuthenticationService {

    private final ISysUserService sysUserService;
    private final ISysRoleService sysRoleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final RedisTemplate<String, Object> redisTemplate;
    private final SecurityConfigurationProperties securityConfigurationProperties;
    @Value("${jwt.access-token-expiration-time}")
    private Integer accessTokenExpirationTime;

    public String register(RegisterFormVo request) {
        Boolean existenceUser = sysUserService.isExistenceUser(request.getUsername());
        if (existenceUser) {
            throw new BusinessException("用户名已存在!");
        }
        String password = passwordEncoder.encode(request.getPassword());
        SysUserDo user = sysUserService.createUser(request.getUsername(), password, request.getNickname());
        Assert.notNull(user, SystemEnum.ERROR_PROMPT.getValue());
        Boolean userDefaultRole = sysRoleService.createUserDefaultRole(user.getId());
        Assert.isTrue(userDefaultRole, SystemEnum.ERROR_PROMPT.getValue());
        return "注册成功";
    }

    public String getAccessTokenByRefreshToken(RefreshTokenVo req) {
        String refreshToken = req.getRefreshToken();
        // 校验token是否有效
        Boolean isCheck = jwtUtils.checkToken(refreshToken);
        if (!isCheck) {
            throw new RefreshTokenExpiredException();
        }
        Map<String, Object> jwtTokenMap = jwtUtils.parseToken(refreshToken);
        String username = (String) jwtTokenMap.get("username");

        SysUser sysUser = sysUserService.queryUserByUsername(username);
        Map<String, Object> sysUserMap = BeanUtil.beanToMap(sysUser);
        sysUserMap.remove("password");
        return jwtUtils.getJwtToken(sysUserMap, accessTokenExpirationTime);
    }

}
