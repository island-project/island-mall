package org.chenzx.island.vo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 权限角色传输对象
 * @date 2022/7/11 19:44
 */
@Data
public class AuthRoleDto {

    /**
     * 用户角色列表
     */
    private List<String> roles;

    /**
     * 用户权限列表
     */
    private List<String> auths;
}
