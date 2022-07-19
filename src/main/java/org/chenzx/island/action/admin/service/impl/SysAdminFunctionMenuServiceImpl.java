package org.chenzx.island.action.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chenzx.island.action.admin.mapper.SysAdminFunctionMenuMapper;
import org.chenzx.island.action.admin.pojo.SysAdminFunctionMenuDo;
import org.chenzx.island.action.admin.service.ISysAdminFunctionMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description sys admin function menu service impl
 * @date 2022/7/11 20:21
 */
@Service
public class SysAdminFunctionMenuServiceImpl extends ServiceImpl<SysAdminFunctionMenuMapper, SysAdminFunctionMenuDo> implements ISysAdminFunctionMenuService {

    /**
     * 查询所有启用 展示的菜单信息
     * 用于控制平台中侧边栏数据查询
     *
     * @return 菜单信息(按照sort_num排序后)
     */
    @Override
    public List<SysAdminFunctionMenuDo> queryAllEnableMenu() {
        LambdaQueryWrapper<SysAdminFunctionMenuDo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysAdminFunctionMenuDo::getIsEnable, true);
        wrapper.eq(SysAdminFunctionMenuDo::getIsShow, true);
        wrapper.orderByAsc(SysAdminFunctionMenuDo::getSortNum);
        return super.list(wrapper);
    }
}
