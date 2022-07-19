package org.chenzx.island.action.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.chenzx.island.action.admin.pojo.SysAdminFunctionMenuDo;

import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description sys admin function menu service
 * @date 2022/7/11 20:19
 */
public interface ISysAdminFunctionMenuService extends IService<SysAdminFunctionMenuDo> {

    /**
     * 查询所有启用 展示的菜单信息
     * 用于控制平台中侧边栏数据查询
     *
     * @return 菜单信息(按照sort_num排序后)
     */
    List<SysAdminFunctionMenuDo> queryAllEnableMenu();

}
