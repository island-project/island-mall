package org.chenzx.island.business;

import lombok.RequiredArgsConstructor;
import org.chenzx.island.enums.SystemEnum;
import org.chenzx.island.exception.BusinessException;
import org.chenzx.island.service.ISysRoleService;
import org.chenzx.island.service.ISysUserService;
import org.chenzx.island.vo.RegisterFormVo;
import org.chenzx.island.vo.pojo.SysUserDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

}
