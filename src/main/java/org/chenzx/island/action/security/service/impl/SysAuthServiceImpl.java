package org.chenzx.island.action.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chenzx.island.action.security.mapper.SysAuthMapper;
import org.chenzx.island.action.security.pojo.SysAuthDo;
import org.chenzx.island.action.security.service.ISysAuthService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description sys_auth service impl
 * @date 2022/7/13 19:56
 */
@Service
public class SysAuthServiceImpl extends ServiceImpl<SysAuthMapper, SysAuthDo> implements ISysAuthService {

    /**
     * 查询全部的权限信息
     *
     * @return 权限列表
     */
    @Override
    public List<SysAuthDo> queryAllSysAuth() {
        LambdaQueryWrapper<SysAuthDo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysAuthDo::getStatus, 1);
        wrapper.orderByDesc(SysAuthDo::getWeight);
        return super.list(wrapper);
    }
}
