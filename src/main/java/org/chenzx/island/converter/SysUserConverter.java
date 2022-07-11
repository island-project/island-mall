package org.chenzx.island.converter;

import org.chenzx.island.vo.SysUser;
import org.chenzx.island.vo.dto.AuthRoleDto;
import org.chenzx.island.vo.pojo.SysUserDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description SysUser对象转换器
 * @date 2022/7/9 13:30
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    /**
     * 将SysUserDo转换为SysUser对象
     *
     * @param sysUserDo do对象
     * @param user      转换的目标对象
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
    @Mapping(
            target = "authorities",
            expression = "java(org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(\",\",java.util.stream.Stream.of(user.getAuths(),user.getRoles()).flatMap(java.util.Collection::stream).collect(java.util.stream.Collectors.toList()))))")
    SysUser doToSysUser(SysUserDo sysUserDo, AuthRoleDto user);

}
