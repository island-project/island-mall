package org.chenzx.island.action.admin.converter;

import org.chenzx.island.action.admin.pojo.MenuVo;
import org.chenzx.island.action.admin.pojo.SysAdminFunctionMenuDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description SysAdminFunctionMenu 转换为 MenuVo
 * @date 2022/7/11 20:56
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysAdminFunctionMenuConverter {

//    SysAdminFunctionMenuConverter INSTANCE = Mappers.getMapper(SysAdminFunctionMenuConverter.class);

    /**
     * 将SysAdminFunctionMenuDo类对象转换为MenuVo
     * 注意:需要自己填充MenuVo的children属性
     *
     * @param menuDo 源对象
     * @return MenuVo
     */
    @Mapping(target = "id", source = "menuDo.id")
    @Mapping(target = "path", source = "menuDo.requestPath")
    @Mapping(target = "component", source = "menuDo.componentPath")
    @Mapping(target = "name", source = "menuDo.key")
    @Mapping(target = "meta", expression = "java(new org.chenzx.island.action.admin.pojo.MenuVo.Meta(menuDo.getName(),menuDo.getIcon()))")
    @Mapping(target = "children", ignore = true)
    MenuVo toMenuVo(SysAdminFunctionMenuDo menuDo);
}
