package org.chenzx.island.action.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.chenzx.island.action.security.converter.SysUserConverter;
import org.chenzx.island.action.security.mapper.SysUserMapper;
import org.chenzx.island.action.security.pojo.SysUser;
import org.chenzx.island.action.security.pojo.SysUserDo;
import org.chenzx.island.action.security.service.ISysUserService;
import org.chenzx.island.common.enums.SystemEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description ISysUserService impl
 * @date 2022/7/9 12:58
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDo> implements ISysUserService {

    private final SysUserMapper sysUserMapper;
    private final SysUserConverter sysUserConverter;
    @Value("${user.default-avatar}")
    private String defaultAvatar;

    /**
     * 查询用户对象
     *
     * @param username 用户名
     * @return 用户对象do
     */
    @Override
    public SysUser queryUserByUsername(String username) {
        LambdaQueryWrapper<SysUserDo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysUserDo::getUsername, username);
        SysUserDo sysUserDo = super.getOne(wrapper);
        if (sysUserDo == null) {
            return null;
        }
        return sysUserConverter.doToSysUser(sysUserDo, sysUserMapper.queryUserAllInfo(sysUserDo.getId()));
    }

    @Override
    public Boolean isExistenceUser(String username) {
        LambdaQueryWrapper<SysUserDo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysUserDo::getUsername, username);
        return super.count(wrapper) > 0;
    }

    /**
     * 创建用户
     *
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 如果成功 返回true
     */
    @Override
    public SysUserDo createUser(String username, String password, String nickname) {
        SysUserDo build = SysUserDo.builder().username(username).password(password).nickname(nickname).avatar(defaultAvatar).build();
        boolean save = super.save(build);
        Assert.isTrue(save, SystemEnum.ERROR_PROMPT.getValue());
        return build;
    }
}
