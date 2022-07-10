package org.chenzx.island.business;

import lombok.RequiredArgsConstructor;
import org.chenzx.island.service.ISysUserService;
import org.chenzx.island.vo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description Spring Security 加载用户信息
 * @date 2022/7/9 12:35
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtTokenUserDetailsServiceImpl implements UserDetailsService {

    private final ISysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.queryUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return sysUser;
    }
}
