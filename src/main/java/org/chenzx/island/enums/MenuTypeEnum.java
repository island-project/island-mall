package org.chenzx.island.enums;

import lombok.Getter;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 菜单类型枚举
 * @date 2022/7/15 21:15
 */
@Getter
public enum MenuTypeEnum {

    /**
     * 枚举字段
     */
    DIRECTORY("目录", 1),
    VIEW("页面", 2),
    EXTERNAL_LINKS("外部链接", 3);

    MenuTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    private final String name;
    private final Integer value;


}
