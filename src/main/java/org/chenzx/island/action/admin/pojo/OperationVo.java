package org.chenzx.island.action.admin.pojo;

import lombok.Data;
import org.chenzx.island.action.security.pojo.SysUser;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 操作请求对象
 * @date 2022/7/19 20:59
 */
@Data
public class OperationVo {

    /**
     * 要操作的菜单的key值
     */
    @NotEmpty(message = "要操作的菜单key值不能为空")
    private String key;
    /**
     * 操作的方法,例如get/post/delete/put
     */
    @NotEmpty(message = "未指定操作菜单的方法")
    private String method;
    /**
     * 要操作的数据的id,如果为post delete put必传
     */
    private List<String> operationIdList;
    /**
     * 新增或修改数据时的数据
     */
    private Object operationData;
    /**
     * 执行该操作的对象
     */
    private SysUser sysUser;
    /**
     * 该属性为get请求时,返回的结果是否需要分页
     */
    private Boolean isNeedPaging;
    /**
     * 一页的条数
     */
    private Integer pageSize;
    /**
     * 当前页
     */
    private Integer pageNo;

}
