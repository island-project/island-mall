package org.chenzx.island.vo;

import lombok.Builder;
import lombok.Data;
import org.chenzx.island.enums.SysResponseCodeEnum;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 全局统一返回对象
 * @date 2022/7/10 9:24
 */
@Data
@Builder
public class Result {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 请求描述信息
     */
    private String msg;
    /**
     * 数据对象
     */
    private Object data;
    /**
     * 执行时间
     */
    private Long execution;

    public static Result isOk(Object data) {
        return Result.builder()
                .code(SysResponseCodeEnum.SUCCESS.getCode())
                .msg(SysResponseCodeEnum.SUCCESS.getMsg())
                .data(data)
                .build();
    }

    public static Result error(Integer code, String msg) {
        return Result.builder()
                .code(code)
                .msg(msg)
                .build();
    }

}
