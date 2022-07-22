package org.chenzx.island.action.admin.controller;

import lombok.RequiredArgsConstructor;
import org.chenzx.island.action.admin.pojo.OperationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 管理平台系统管理接口
 * @date 2022/7/19 20:53
 */
@RestController
@RequestMapping("/admin/manage")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManageController {

    /**
     * 针对管理平台中任何菜单的获取数据 修改数据 删除数据的统一操作接口
     *
     * @return
     */
    @PostMapping("/operation")
    public Object operationRequest(@RequestBody @Validated OperationVo operation) {
        return null;
    }

}
