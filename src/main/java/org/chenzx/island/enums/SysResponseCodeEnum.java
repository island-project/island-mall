package org.chenzx.island.enums;

import lombok.Getter;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 系统返回状态码枚举
 * @date 2022/7/10 9:28
 */
@Getter
public enum SysResponseCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200, "成功!"),
    ERROR(500, "失败"),

    // =================  Spring Security  =================
    SECURITY_USERNAME_PASSWORD_ERROR(10000, "用户名或密码错误"),

    SECURITY_LOGIN_REQUIRED(10001, "需要登录"),
    SECURITY_UNAUTHORIZED_ACCESS(10002, "无权限访问"),
    SECURITY_TOKEN_INVALID(10003, "token无效或已经过期");

    SysResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 描述信息
     */
    private final String msg;

}
