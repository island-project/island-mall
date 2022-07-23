package org.chenzx.island.action.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.chenzx.island.action.admin.business.MallProductManageService;
import org.chenzx.island.action.admin.pojo.GetProductInfoVo;
import org.chenzx.island.action.admin.pojo.SearchProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 商城商品管理接口
 * @date 2022/7/23 12:22
 */
@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MallProductManageController {

    private final MallProductManageService mallProductManageService;

    @GetMapping("/getProductList")
    public IPage<GetProductInfoVo> getMallManageProductList(@Validated SearchProductVo searchProduct) {
        return mallProductManageService.getMallManageProductList(searchProduct);
    }

}
