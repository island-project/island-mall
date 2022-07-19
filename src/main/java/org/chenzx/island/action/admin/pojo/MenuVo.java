package org.chenzx.island.action.admin.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 获取系统菜单列表返回对象
 * @date 2022/7/11 18:16
 */
@Data
@Builder
@EqualsAndHashCode
public class MenuVo {

    @JsonIgnore
    private String id;

    private String path;

    private String component;

    private String name;

    private Meta meta;

    private List<MenuVo> children;

    @Data
    @AllArgsConstructor
    public static class Meta {
        private String title;
        private String icon;
    }

}
