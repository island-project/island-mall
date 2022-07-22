package org.chenzx.island.action.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description Spring Security 状态码(认证相关)
 * @date 2022/7/22 10:17
 */
@Getter
@AllArgsConstructor
public enum SecurityEnum {

    // =================  Spring Security  =================
    SECURITY_USERNAME_PASSWORD_ERROR(10000, "用户名或密码错误"),

    SECURITY_LOGIN_REQUIRED(10001, "需要登录"),
    SECURITY_UNAUTHORIZED_ACCESS(10002, "无权限访问"),
    SECURITY_TOKEN_EXPIRED(10003, "token过期"),
    SECURITY_INVALID_TOKEN(10004, "token无效"),
    SECURITY_REFRESH_TOKEN_EXPIRED(10005, "用户认证身份过期,请重新登录"),
    SECURITY_OTHER_ERRORS(10006, null);

    private final Integer code;
    private final String msg;

}
