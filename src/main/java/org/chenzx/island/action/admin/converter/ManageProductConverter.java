package org.chenzx.island.action.admin.converter;

import org.chenzx.island.action.admin.pojo.GetProductInfoVo;
import org.chenzx.island.action.admin.pojo.MallProductDo;
import org.chenzx.island.action.admin.pojo.ProductSkuVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 管理商品相关的转换器
 * @date 2022/7/23 13:16
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ManageProductConverter {

    /**
     * 将商品信息和sku信息转换为GetProductInfoVo类对象
     *
     * @param product 商品信息
     * @param skuInfo sku信息
     * @return vo结果对象
     */
    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "salesVolume", source = "product.salesVolume")
    @Mapping(target = "isRecommend", source = "product.isRecommend")
    @Mapping(target = "type", source = "product.type")
    @Mapping(target = "sortNum", source = "product.sortNum")
    @Mapping(target = "isNew", source = "product.isNew")
    @Mapping(target = "brand", source = "product.brand")
    @Mapping(target = "isInStock", source = "product.isInStock")
    @Mapping(target = "mainImage", source = "product.mainImage")
    @Mapping(target = "artNo", source = "product.artNo")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "skuInfo", source = "skuInfo")
    GetProductInfoVo toGetProductInfoVo(MallProductDo product, ProductSkuVo skuInfo);

}
