package org.chenzx.island.action.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.chenzx.island.action.admin.pojo.MallProductDo;
import org.chenzx.island.action.admin.pojo.SearchProductVo;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description MallProduct Service
 * @date 2022/7/23 12:27
 */
public interface IMallProductService extends IService<MallProductDo> {

    /**
     * 分页查询商品信息
     *
     * @param searchProduct 要查询的字段
     * @return 查询结果
     */
    IPage<MallProductDo> queryProductInformationByPage(SearchProductVo searchProduct);

}
