package org.chenzx.island.converter;

import org.chenzx.island.vo.SysUser;
import org.chenzx.island.vo.pojo.SysUserDo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
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
    SysUser doToSysUser(SysUserDo sysUserDo, @MappingTarget SysUser user);

}
