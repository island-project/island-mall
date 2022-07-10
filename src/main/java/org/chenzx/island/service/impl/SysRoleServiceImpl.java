package org.chenzx.island.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.chenzx.island.mapper.SysRoleMapper;
import org.chenzx.island.service.ISysRoleService;
import org.chenzx.island.vo.pojo.SysRoleDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description sys_role service impl
 * @date 2022/7/10 17:29
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleDo> implements ISysRoleService {

    private final SysRoleMapper sysRoleMapper;


    @Override
    public Boolean createUserDefaultRole(String userId) {
        SysRoleDo sysRoleDo = queryDefaultRole();
        String roleId = sysRoleDo.getId();
        Integer integer = sysRoleMapper.insertContrastLog(userId, roleId);
        return integer > 0;
    }

    private SysRoleDo queryDefaultRole() {
        LambdaQueryWrapper<SysRoleDo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysRoleDo::getIsDefaultRole, 1);
        wrapper.select(SysRoleDo::getId);
        return super.getOne(wrapper);
    }
}
