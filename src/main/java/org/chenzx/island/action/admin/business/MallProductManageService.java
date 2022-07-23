package org.chenzx.island.action.admin.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.chenzx.island.action.admin.converter.ManageProductConverter;
import org.chenzx.island.action.admin.pojo.GetProductInfoVo;
import org.chenzx.island.action.admin.pojo.MallProductDo;
import org.chenzx.island.action.admin.pojo.SearchProductVo;
import org.chenzx.island.action.admin.service.IMallProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 商城商品管理接口业务服务类
 * @date 2022/7/23 13:10
 */
@Service
@Transactional(rollbackFor = {Exception.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MallProductManageService {

    private final IMallProductService mallProductService;
    private final ManageProductConverter manageProductConverter;

    public IPage<GetProductInfoVo> getMallManageProductList(SearchProductVo searchProduct) {
        IPage<MallProductDo> mallProduct = mallProductService.queryProductInformationByPage(searchProduct);
        List<MallProductDo> mallProductList = mallProduct.getRecords();
        List<GetProductInfoVo> result = mallProductList.stream().map(v -> {
            // TODO 获取当前商品的sku属性,当前暂时没有sku相关逻辑,预留在这
            return manageProductConverter.toGetProductInfoVo(v, null);
        }).collect(Collectors.toList());
        Page<GetProductInfoVo> page = Page.of(mallProduct.getCurrent(), mallProduct.getSize(), mallProduct.getTotal());
        return page.setRecords(result);
    }

}
