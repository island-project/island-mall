package org.chenzx.island.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.chenzx.island.vo.pojo.SysRoleDo;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description sys_role service
 * @date 2022/7/10 17:29
 */
public interface ISysRoleService extends IService<SysRoleDo> {

    /**
     * 创建默认的角色
     *
     * @param userId 用户主键
     * @return true为成功
     */
    Boolean createUserDefaultRole(String userId);

}
