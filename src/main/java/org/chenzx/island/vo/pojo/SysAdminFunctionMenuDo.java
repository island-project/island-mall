package org.chenzx.island.vo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 系统管理功能菜单
 * @date 2022/7/9 12:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_admin_function_menu")
public class SysAdminFunctionMenuDo {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 父菜单id
     */
    @TableField(value = "father_id")
    private String fatherId;

    /**
     * 当前菜单级别
     */
    @TableField(value = "`level`")
    private Integer level;

    /**
     * 菜单类型
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 菜单名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 请求地址(路由地址)
     */
    @TableField(value = "request_path")
    private String requestPath;

    /**
     * 组件地址
     */
    @TableField(value = "`component_path`")
    private String componentPath;

    /**
     * 菜单的key
     */
    @TableField(value = "`key`")
    private String key;

    /**
     * 权限标识
     */
    @TableField(value = "auth_flag")
    private String authFlag;

    /**
     * 是否显示在侧边栏中
     */
    @TableField(value = "is_show")
    private Boolean isShow;

    /**
     * 是否启用
     */
    @TableField(value = "is_enable")
    private Boolean isEnable;

    /**
     * 排序字段
     */
    @TableField(value = "sort_num")
    private Integer sortNum;

    /**
     * 逻辑删除标记
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    public static final String COL_ID = "id";

    public static final String COL_FATHER_ID = "father_id";

    public static final String COL_LEVEL = "level";

    public static final String COL_TYPE = "type";

    public static final String COL_ICON = "icon";

    public static final String COL_NAME = "name";

    public static final String COL_REQUEST_PATH = "request_path";

    public static final String COL_COMPONENT_PATH = "component_path";

    public static final String COL_KEY = "key";

    public static final String COL_AUTH_FLAG = "auth_flag";

    public static final String COL_IS_SHOW = "is_show";

    public static final String COL_IS_ENABLE = "is_enable";

    public static final String COL_SORT_NUM = "sort_num";

    public static final String COL_DEL_FLAG = "del_flag";
}
