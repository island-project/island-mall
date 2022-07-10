package org.chenzx.island.vo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description sys_auth
 * @date 2022/7/9 12:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_auth")
public class SysAuthDo {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 权限名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 能够访问的url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 权限状态
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 中文描述
     */
    @TableField(value = "`zh_desc`")
    private String zhDesc;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "del_flag")
    private Boolean delFlag;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_URL = "url";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DEL_FLAG = "del_flag";
}
