package org.chenzx.island.action.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.chenzx.island.action.security.pojo.SysRoleDo;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description SysRoleMapper
 * @date 2022/7/9 12:35
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleDo> {

    /**
     * 插入用户、角色关联记录
     *
     * @param userId 用户主键
     * @param roleId 权限主键
     * @return >0 成功
     */
    @Insert("insert into sys_user_role (user_id, role_id) values (#{userId},#{roleId})")
    Integer insertContrastLog(String userId, String roleId);

}
