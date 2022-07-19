package org.chenzx.island.action.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.chenzx.island.action.security.pojo.SysAuthDo;

import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description sys_auth service
 * @date 2022/7/13 19:55
 */
public interface ISysAuthService extends IService<SysAuthDo> {

    /**
     * 查询全部的权限信息
     *
     * @return 权限列表
     */
    List<SysAuthDo> queryAllSysAuth();

}
