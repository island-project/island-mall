package org.chenzx.island.common.enums;

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
    ERROR(500, "内部服务器错误,请联系管理员"),
    REQUEST_PARAMETER_EXCEPTION(-1, "请求参数异常");

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
