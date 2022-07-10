package org.chenzx.island.controller;

import lombok.RequiredArgsConstructor;
import org.chenzx.island.business.SysAuthService;
import org.chenzx.island.vo.RegisterFormVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 系统登录、退出、刷新token等接口
 * @date 2022/7/10 13:33
 */
@RestController
@RequestMapping("/sys/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysAuthController {

    private final SysAuthService sysAuthService;

    /**
     * 注册接口
     *
     * @param request 请求对象
     * @return
     */
    @PostMapping(value = "/register")
    public String register(@RequestBody @Validated RegisterFormVo request) {
        return sysAuthService.register(request);
    }

}
