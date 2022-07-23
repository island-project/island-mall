package org.chenzx.island.action.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chenzx.island.action.admin.mapper.MallProductMapper;
import org.chenzx.island.action.admin.pojo.MallProductDo;
import org.chenzx.island.action.admin.pojo.SearchProductVo;
import org.chenzx.island.action.admin.service.IMallProductService;
import org.springframework.stereotype.Service;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description MallProduct Service Impl
 * @date 2022/7/23 12:28
 */
@Service
public class MallProductServiceImpl extends ServiceImpl<MallProductMapper, MallProductDo> implements IMallProductService {

    /**
     * 分页查询商品信息
     *
     * @param searchProduct 要查询的字段
     * @return 查询结果
     */
    @Override
    public IPage<MallProductDo> queryProductInformationByPage(SearchProductVo searchProduct) {
        Page<MallProductDo> page = Page.of(searchProduct.getCurrentPage(), searchProduct.getSizePage());
        LambdaQueryWrapper<MallProductDo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(searchProduct.getArtNo() != null, MallProductDo::getArtNo, searchProduct.getArtNo());
        wrapper.eq(searchProduct.getType() != null, MallProductDo::getType, searchProduct.getType());
        wrapper.eq(searchProduct.getBrand() != null, MallProductDo::getBrand, searchProduct.getBrand());
        wrapper.eq(searchProduct.getIsInStock() != null, MallProductDo::getIsInStock, searchProduct.getIsInStock());
        wrapper.eq(searchProduct.getIsRecommend() != null, MallProductDo::getIsRecommend, searchProduct.getIsRecommend());
        wrapper.like(StrUtil.isNotBlank(searchProduct.getName()), MallProductDo::getName, searchProduct.getName());

        return super.page(page, wrapper);
    }
}
