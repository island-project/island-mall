package org.chenzx.island.action.admin.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 前端筛选搜索接口入参对象
 * @date 2022/7/23 12:53
 */
@Data
public class SearchProductVo {

    /**
     * 当前页
     */
    @NotNull(message = "分页字段currentPage不能为空")
    private Long currentPage;
    /**
     * 每页显示的条数
     */
    @NotNull(message = "分页字段sizePage不能为空")
    private Long sizePage;

    /**
     * 商品名称
     */
    private String name;
    /**
     * 货号
     */
    private Long artNo;
    /**
     * 分类
     */
    private Integer type;
    /**
     * 品牌
     */
    private Integer brand;
    /**
     * 是上架商品
     */
    private Boolean isInStock;
    /**
     * 是推荐商品
     */
    private Boolean isRecommend;

}
