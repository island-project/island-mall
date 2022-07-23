package org.chenzx.island.action.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商城商品表
 *
 * @author 陈泽宣
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall_product")
public class MallProductDo {
    /**
     * 商品编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 品牌
     */
    @TableField(value = "brand")
    private Integer brand;

    /**
     * 商品类别
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 货号
     */
    @TableField(value = "art_no")
    private Long artNo;

    /**
     * 是上架商品
     */
    @TableField(value = "is_in_stock")
    private Boolean isInStock;

    /**
     * 是新品
     */
    @TableField(value = "is_new")
    private Boolean isNew;

    /**
     * 是推荐商品
     */
    @TableField(value = "is_recommend")
    private Boolean isRecommend;

    /**
     * 排序字段
     */
    @TableField(value = "sort_num")
    private Integer sortNum;

    /**
     * 销量
     */
    @TableField(value = "sales_volume")
    private String salesVolume;

    /**
     * 商品主图
     */
    @TableField(value = "main_image")
    private String mainImage;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_BRAND = "brand";

    public static final String COL_PRICE = "price";

    public static final String COL_ART_NO = "art_no";

    public static final String COL_IS_IN_STOCK = "is_in_stock";

    public static final String COL_IS_NEW = "is_new";

    public static final String COL_IS_RECOMMEND = "is_recommend";

    public static final String COL_SORT_NUM = "sort_num";

    public static final String COL_SALES_VOLUME = "sales_volume";

    public static final String COL_MAIN_IMAGE = "main_image";
}
