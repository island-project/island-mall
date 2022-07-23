package org.chenzx.island.action.system.controller;

import lombok.RequiredArgsConstructor;
import org.chenzx.island.action.system.business.SysDictionaryService;
import org.chenzx.island.common.pojo.DictionaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 系统字典接口
 * @date 2022/7/23 19:37
 */
@RestController
@RequestMapping("/sys/dict")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictionaryController {

    private final SysDictionaryService sysDictionaryService;

    @GetMapping("/get")
    public List<DictionaryVo> getDictionaryById(@RequestParam @Validated @NotNull(message = "系统字典id不能为空") Long id) {
        return sysDictionaryService.getDictionaryById(id);
    }

}
