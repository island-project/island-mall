package org.chenzx.island.action.system.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chenzx.island.action.system.service.ISysDictionary;
import org.chenzx.island.common.pojo.DictionaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 系统字典业务服务类
 * @date 2022/7/23 19:46
 */
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictionaryService {

    private final ISysDictionary sysDictionary;

    public List<DictionaryVo> getDictionaryById(Long id) {
        return sysDictionary.queryOneDict(id);
    }

}
