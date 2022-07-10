package org.chenzx.island.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.chenzx.island.converter.SysUserConverter;
import org.chenzx.island.mapper.SysUserMapper;
import org.chenzx.island.service.ISysUserService;
import org.chenzx.island.vo.SysUser;
import org.chenzx.island.vo.pojo.SysUserDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
