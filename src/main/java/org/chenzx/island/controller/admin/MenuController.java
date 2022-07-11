package org.chenzx.island.controller.admin;

import lombok.RequiredArgsConstructor;
import org.chenzx.island.business.admin.MenuService;
import org.chenzx.island.vo.SysUser;
import org.chenzx.island.vo.admin.MenuVo;
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
