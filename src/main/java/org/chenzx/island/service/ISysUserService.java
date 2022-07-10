package org.chenzx.island.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.chenzx.island.vo.SysUser;
import org.chenzx.island.vo.pojo.SysUserDo;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description SysUserService
 * @date 2022/7/9 12:57
 */
public interface ISysUserService extends IService<SysUserDo> {

    /**
     * 查询用户对象
     *
     * @param username 用户名
     * @return 用户对象do
     */
    SysUser queryUserByUsername(String username);

}
