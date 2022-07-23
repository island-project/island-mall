package org.chenzx.island.action.admin.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 管理平台获取管理商品 查询商品信息接口返回对象
 * @date 2022/7/23 12:24
 */
@Data
@EqualsAndHashCode
public class GetProductInfoVo {

    /**
     * 商品编号
     */
    private Long id;

    /**
     * 商品名字
     */
    private String name;

    /**
     * 商品类型
     */
    private Integer type;

    /**
     * 品牌
     */
    private Integer brand;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 货号
     */
    private Long artNo;

    /**
     * 是上架商品
     */
    private Boolean isInStock;

    /**
     * 是新品
     */
    private Boolean isNew;

    /**
     * 是推荐商品
     */
    private Boolean isRecommend;

    /**
     * 排序字段
     */
    private Integer sortNum;

    /**
     * 销量
     */
    private String salesVolume;

    /**
     * 商品主图
     */
    private String mainImage;

    /**
     * 商品的sku信息
     */
    private ProductSkuVo skuInfo;

}
