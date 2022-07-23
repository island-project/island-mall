package org.chenzx.island.action.system.pojo;

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
 * 系统字典配置表,该表应当尽量减少修改
 *
 * @author qchen
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dictionary")
public class SysDictionaryDo {
    /**
     * 字典主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 是根字典
     */
    @TableField(value = "is_root")
    private Boolean isRoot;

    /**
     * 当前数据的级数
     */
    @TableField(value = "`level`")
    private Integer level;

    /**
     * 父级id
     */
    @TableField(value = "parent_id")
    private Long parentId;


    /**
     * 字典的标签(前端展示的文字)
     */
    @TableField(value = "`label`")
    private String label;

    /**
     * 字典值
     */
    @TableField(value = "`value`")
    private String value;

    /**
     * 字典其他信息json
     */
    @TableField(value = "data_json")
    private String dataJson;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 是否启用
     */
    @TableField(value = "is_enable")
    private Boolean isEnable;

    /**
     * 排序字段,如果是根字典,请设置为null
     */
    @TableField(value = "sort_num")
    private Integer sortNum;

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
     * 删除标记
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    public static final String COL_ID = "id";

    public static final String COL_IS_ROOT = "is_root";

    public static final String COL_LEVEL = "level";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_LABEL = "label";

    public static final String COL_VALUE = "value";

    public static final String COL_DATA_JSON = "data_json";

    public static final String COL_REMARK = "remark";

    public static final String COL_IS_ENABLE = "is_enable";

    public static final String COL_SORT_NUM = "sort_num";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DEL_FLAG = "del_flag";
}
