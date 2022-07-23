package org.chenzx.island.action.admin.business;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chenzx.island.action.admin.converter.SysAdminFunctionMenuConverter;
import org.chenzx.island.action.admin.pojo.MenuVo;
import org.chenzx.island.action.admin.pojo.SysAdminFunctionMenuDo;
import org.chenzx.island.action.admin.service.ISysAdminFunctionMenuService;
import org.chenzx.island.action.security.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.chenzx.island.action.admin.enums.MenuTypeEnum.*;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 菜单接口 Service
 * @date 2022/7/11 20:15
 */
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuService {

    private final ISysAdminFunctionMenuService sysAdminFunctionMenuService;
    private final SysAdminFunctionMenuConverter sysAdminFunctionMenuConverter;

    public List<MenuVo> getMenu(SysUser user) {
        List<MenuVo> result = Lists.newArrayList();
        // 查询出全部的菜单
        List<SysAdminFunctionMenuDo> allMenus = sysAdminFunctionMenuService.queryAllEnableMenu();
        // TODO 此处需要添加权限过滤逻辑
        // 按照菜单级别分组
        Map<Integer, List<SysAdminFunctionMenuDo>> levelMap = allMenus.stream().collect(Collectors.groupingBy(SysAdminFunctionMenuDo::getLevel));
        List<MenuVo> rootMenu = levelMap.get(0).stream().map(v -> {
            // 如果是一级页面(一级目录就是页面)
            if (VIEW.getValue().equals(v.getType())) {
                return MenuVo.builder()
                        .path("/")
                        .component("Layout")
                        .children(
                                Lists.newArrayList(MenuVo.builder()
                                        .name(v.getName())
                                        .path(v.getRequestPath())
                                        .component(v.getComponentPath())
                                        .meta(new MenuVo.Meta(v.getName()
                                                , v.getIcon())).build())
                        ).build();
            } else {
                return sysAdminFunctionMenuConverter.toMenuVo(v);
            }
        }).collect(Collectors.toList());
        recursiveAssemblyMenu(rootMenu, levelMap, 0);
        return rootMenu;
    }

    /**
     * 使用递归来组装菜单
     *
     * @param menus 当前级别下的菜单
     * @param all   系统中全部的菜单
     * @param level menus中的level(等级)
     */
    private void recursiveAssemblyMenu(List<MenuVo> menus, Map<Integer, List<SysAdminFunctionMenuDo>> all, Integer level) {
        if (all.get(level + 1) == null) {
            return;
        }
        // 待组装的level + 1级的菜单
        List<SysAdminFunctionMenuDo> menuList = all.get(level + 1);
        // level + 1 层级下的所有菜单
        List<MenuVo> list = Lists.newArrayList();
        for (MenuVo menu : menus) {
            List<MenuVo> collect = menuList.stream()
                    .filter(v -> v.getFatherId().equals(menu.getId()))
                    .map(v -> {
                        // 如果是外链 需要特殊处理
                        if (EXTERNAL_LINKS.getValue().equals(v.getType())) {
                            return MenuVo.builder()
                                    .id(v.getId())
                                    .path(v.getRequestPath())
                                    .component("Layout")
                                    .children(
                                            Lists.newArrayList(MenuVo.builder().path(v.getComponentPath()).meta(new MenuVo.Meta(v.getName(), v.getIcon())).build())
                                    )
                                    .build();
                        } else if (DIRECTORY.getValue().equals(v.getType()) || VIEW.getValue().equals(v.getType())) {
                            return sysAdminFunctionMenuConverter.toMenuVo(v);
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
            menu.setChildren(collect);
            list.addAll(collect);
        }
        // 递归调用 去组装下一级的目录
        recursiveAssemblyMenu(list, all, level + 1);
    }

}
