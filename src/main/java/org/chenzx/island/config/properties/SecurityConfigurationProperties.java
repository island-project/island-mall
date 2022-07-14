package org.chenzx.island.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description yml 文件中 security 配置信息
 * @date 2022/7/13 20:26
 */
@Data
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityConfigurationProperties {

    /**
     * 权限认证是排除的权限
     */
    private List<String> excludeUrl;

    /**
     * redis中存放用户登录状态的key
     */
    private String redisKey;

}
