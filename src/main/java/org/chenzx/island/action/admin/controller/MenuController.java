package org.chenzx.island.action.admin.controller;

import lombok.RequiredArgsConstructor;
import org.chenzx.island.action.admin.business.MenuService;
import org.chenzx.island.action.admin.pojo.MenuVo;
import org.chenzx.island.action.security.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 菜单接口控制器
 * @date 2022/7/11 18:15
 */
@RestController
@RequestMapping("/admin/menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/getMenu")
    public List<MenuVo> getMenu(@AuthenticationPrincipal SysUser user) {
        return menuService.getMenu(user);
    }

}
