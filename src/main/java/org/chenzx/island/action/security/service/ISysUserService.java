package org.chenzx.island.action.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.chenzx.island.action.security.pojo.SysUser;
import org.chenzx.island.action.security.pojo.SysUserDo;

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

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return 如果存在返回true
     */
    Boolean isExistenceUser(String username);

    /**
     * 创建用户
     *
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 如果成功 返回true
     */
    SysUserDo createUser(String username, String password, String nickname);

}
