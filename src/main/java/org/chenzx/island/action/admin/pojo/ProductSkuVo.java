package org.chenzx.island.action.admin.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 商品sku vo对象
 * @date 2022/7/23 12:31
 */
@Data
@EqualsAndHashCode
public class ProductSkuVo {

    /**
     * sku 编号
     */
    private Long skuNo;

    /**
     * 该sku商品的属性,例如颜色、尺码等
     */
    private List<String> attribute;

    /**
     * 该sku商品的属性值,map中的key为attribute中的内容
     */
    private Map<String, Object> attributeValue;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 商品库存
     */
    private Long stock;

    /**
     * 库存预警值
     */
    private Long warnStockValue;
}
