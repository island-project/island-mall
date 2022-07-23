package org.chenzx.island.action.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.chenzx.island.action.system.pojo.SysDictionaryDo;
import org.chenzx.island.common.pojo.DictionaryVo;

import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description SysDictionary Service
 * @date 2022/7/23 19:49
 */
public interface ISysDictionary extends IService<SysDictionaryDo> {

    /**
     * 根据id查询字典的内容
     *
     * @param id 字典id
     * @return 该id的字典内容
     */
    List<DictionaryVo> queryOneDict(Long id);

}
