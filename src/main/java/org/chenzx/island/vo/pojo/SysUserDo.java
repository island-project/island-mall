package org.chenzx.island.vo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description sys_user
 * @date 2022/7/9 12:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class SysUserDo {

    /**
     * 用户主键
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 指用户的账户是否已过期
     */
    private Boolean isAccountNonExpired;

    /**
     * 指用户的账户是否已被锁定
     */
    private Boolean isAccountNonLocked;

    /**
     * 指用户的密码是否已过期
     */
    private Boolean isCredentialsNonExpired;

    /**
     * 指用户是否已被禁用
     */
    private Boolean isEnabled;
}
