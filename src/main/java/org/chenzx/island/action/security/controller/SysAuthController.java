package org.chenzx.island.action.security.controller;

import lombok.RequiredArgsConstructor;
import org.chenzx.island.action.security.business.SysAuthenticationService;
import org.chenzx.island.action.security.pojo.RefreshTokenVo;
import org.chenzx.island.action.security.pojo.RegisterFormVo;
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

    private final SysAuthenticationService sysAuthenticationService;

    /**
     * 注册接口
     *
     * @param request 请求对象
     * @return
     */
    @PostMapping(value = "/register")
    public String register(@RequestBody @Validated RegisterFormVo request) {
        return sysAuthenticationService.register(request);
    }

    /**
     * 刷新令牌接口
     *
     * @param req 请求参数
     * @return 新的access token
     */
    @PostMapping(value = "/refreshToken")
    public String getAccessTokenByRefreshToken(@RequestBody @Validated RefreshTokenVo req) {
        return sysAuthenticationService.getAccessTokenByRefreshToken(req);
    }

}
