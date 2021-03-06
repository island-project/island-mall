package org.chenzx.island.action.security.converter;

import org.chenzx.island.action.security.pojo.SysUser;
import org.chenzx.island.action.security.pojo.SysUserDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description SysUser对象转换器
 * @date 2022/7/9 13:30
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysUserConverter {

//    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    /**
     * 将SysUserDo转换为SysUser对象
     *
     * @param sysUserDo   do对象
     * @param authorities 用户权限集合
     * @return SysUser对象
     */
    @Mapping(target = "id", source = "sysUserDo.id")
    @Mapping(target = "username", source = "sysUserDo.username")
    @Mapping(target = "password", source = "sysUserDo.password")
    @Mapping(target = "nickname", source = "sysUserDo.nickname")
    @Mapping(target = "avatar", source = "sysUserDo.avatar")
    @Mapping(target = "isAccountNonExpired", source = "sysUserDo.isAccountNonExpired")
    @Mapping(target = "isAccountNonLocked", source = "sysUserDo.isAccountNonLocked")
    @Mapping(target = "isCredentialsNonExpired", source = "sysUserDo.isCredentialsNonExpired")
    @Mapping(target = "isEnabled", source = "sysUserDo.isEnabled")
    @Mapping(target = "authorities", source = "authorities")
    SysUser doToSysUser(SysUserDo sysUserDo, List<GrantedAuthority> authorities);

}
