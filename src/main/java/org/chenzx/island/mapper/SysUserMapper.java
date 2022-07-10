package org.chenzx.island.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chenzx.island.vo.SysUser;
import org.chenzx.island.vo.pojo.SysUserDo;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description SysUserMapper
 * @date 2022/7/9 12:35
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserDo> {

    /**
     * 查询用户全部(权限、角色)附属信息
     *
     * @param userId 用户主键
     * @return 包含用户权限、角色信息的对象
     */
    SysUser queryUserAllInfo(String userId);

}
