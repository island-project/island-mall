package org.chenzx.island.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 获取系统字典接口返回对象
 * @date 2022/7/23 19:43
 */
@Data
@Builder
public class DictionaryVo {

    /**
     * id
     */
    @JsonIgnore
    private Long id;
    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典编码
     */
    private String value;

    /**
     * 子级
     */
    private List<DictionaryVo> children;

}
