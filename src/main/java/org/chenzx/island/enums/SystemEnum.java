package org.chenzx.island.enums;

import lombok.Getter;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 系统枚举
 * @date 2022/7/10 10:30
 */
@Getter
public enum SystemEnum {

    /**
     * token
     */
    TOKEN_HEADER("Authorization", "token在http header中的key"),
    ERROR_PROMPT("系统内部出现错误,请联系管理员或重试", "错误提示");

    SystemEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private final String value;

    private final String name;

}
