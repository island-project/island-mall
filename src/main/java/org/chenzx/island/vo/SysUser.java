package org.chenzx.island.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 用户对象
 * @date 2022/7/9 13:13
 */
@Data
public class SysUser implements UserDetails {

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
    @JsonIgnore
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

    private List<? extends GrantedAuthority> authorities;

    private List<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
