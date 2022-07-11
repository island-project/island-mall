package org.chenzx.island.business.admin;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chenzx.island.converter.SysAdminFunctionMenuConverter;
import org.chenzx.island.service.ISysAdminFunctionMenuService;
import org.chenzx.island.vo.SysUser;
import org.chenzx.island.vo.admin.MenuVo;
import org.chenzx.island.vo.pojo.SysAdminFunctionMenuDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        // 按照菜单级别分组
        Map<Integer, List<SysAdminFunctionMenuDo>> levelMap = allMenus.stream().collect(Collectors.groupingBy(SysAdminFunctionMenuDo::getLevel));
        List<MenuVo> current = null;
        for (Integer level : levelMap.keySet()) {
            if (level.equals(0)) {
                List<MenuVo> firstMenu = levelMap.get(level).stream().map(sysAdminFunctionMenuConverter::toMenuVo).collect(Collectors.toList());
                result.addAll(firstMenu);
                current = firstMenu;
            }
            if (levelMap.get(level + 1) == null) {
                break;
            }
            current = assemblyTree(current, levelMap.get(level + 1));
        }
        return result;
    }

    /**
     * 组装树
     *
     * @param current 当前级别的树
     * @param next    下一级别的树
     * @return 下一级别的树组装好的对象
     */
    private List<MenuVo> assemblyTree(List<MenuVo> current, List<SysAdminFunctionMenuDo> next) {
        List<MenuVo> list = Lists.newArrayList();
        Map<String, List<SysAdminFunctionMenuDo>> nextMap = next.stream().collect(Collectors.groupingBy(SysAdminFunctionMenuDo::getFatherId));
        for (MenuVo menu : current) {
            List<MenuVo> nextResult = nextMap.get(menu.getId()).stream().map(sysAdminFunctionMenuConverter::toMenuVo).collect(Collectors.toList());
            if (nextResult.size() < 1) {
                continue;
            }
            menu.setChildren(nextResult);
            list.addAll(nextResult);
        }
        return list;
    }

}
