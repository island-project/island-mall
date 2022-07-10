package org.chenzx.island.vo;

import lombok.Data;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 注册请求对象 vo
 * @date 2022/7/10 17:09
 */
@Data
public class RegisterFormVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

}
