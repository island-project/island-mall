package org.chenzx.island.action.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.chenzx.island.action.system.mapper.SysDictionaryMapper;
import org.chenzx.island.action.system.pojo.SysDictionaryDo;
import org.chenzx.island.action.system.service.ISysDictionary;
import org.chenzx.island.common.pojo.DictionaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description SysDictionary Service Impl
 * @date 2022/7/23 19:50
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictionaryImpl extends ServiceImpl<SysDictionaryMapper, SysDictionaryDo> implements ISysDictionary {

    private final RedisTemplate<String, Object> redisTemplate;
    /**
     * redis 中系统字典的key
     */
    private static final String SYS_DICT_REDIS_KEY = "sys_dict:all_dict";

    @Override
    public List<DictionaryVo> queryOneDict(Long id) {
        List<SysDictionaryDo> dict;
        String json = (String) redisTemplate.opsForValue().get(SysDictionaryImpl.SYS_DICT_REDIS_KEY);
        if (StrUtil.isEmpty(json)) {
            LambdaQueryWrapper<SysDictionaryDo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(SysDictionaryDo::getIsEnable, true);
            wrapper.orderByAsc(SysDictionaryDo::getSortNum);
            dict = super.list(wrapper);
            redisTemplate.opsForValue().set(SysDictionaryImpl.SYS_DICT_REDIS_KEY, JSON.toJSONString(dict), 2, TimeUnit.MINUTES);
        } else {
            dict = JSON.parseArray(json, SysDictionaryDo.class);
        }


        Map<Integer, List<SysDictionaryDo>> map = dict.stream().collect(Collectors.groupingBy(SysDictionaryDo::getLevel));
        List<DictionaryVo> result = map.get(1).stream()
                .filter(v -> v.getParentId().equals(id))
                .map(v -> DictionaryVo.builder()
                        .id(v.getId()).label(v.getLabel()).value(v.getValue()).build()).collect(Collectors.toList());
        structureResultList(result, map, 1);
        return result;
    }

    private void structureResultList(List<DictionaryVo> result, Map<Integer, List<SysDictionaryDo>> map, Integer level) {
        List<SysDictionaryDo> list = map.get(level + 1);
        if (list == null || list.size() < 1) {
            return;
        }
        List<DictionaryVo> nextList = Lists.newArrayList();

        for (DictionaryVo dictItem : result) {
            List<SysDictionaryDo> dictionary = list.stream()
                    .filter(v -> v.getParentId().equals(dictItem.getId())).collect(Collectors.toList());
            List<DictionaryVo> collect = dictionary.stream().map(v -> DictionaryVo.builder()
                    .id(v.getId()).label(v.getLabel()).value(v.getValue()).build()).collect(Collectors.toList());
            dictItem.setChildren(collect);
            structureResultList(collect, map, level + 1);
        }
    }
}
