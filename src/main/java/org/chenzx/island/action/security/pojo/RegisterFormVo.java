package org.chenzx.island.action.security.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Min(value = 8, message = "密码长度必须大于8位")
    private String password;

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")
    private String nickname;

}
