package org.chenzx.island.action.security.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description 刷新令牌接口请求对象
 * @date 2022/7/14 15:44
 */
@Data
public class RefreshTokenVo {

    /**
     * 刷新令牌
     */
    @NotEmpty(message = "refreshToken不能为空")
    private String refreshToken;

}
