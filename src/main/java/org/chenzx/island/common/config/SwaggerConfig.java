package org.chenzx.island.common.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description swagger 配置
 * @date 2022/7/15 9:31
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    /**
     * 在yml配置文件中指定是否开始swagger
     */
    @Value("${swagger.enabled}")
    Boolean enableSwagger;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.chenzx.island.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("陈泽宣", "https://cunyu1943.github.io", "747731461@qq.com");
        return new ApiInfo(
                "island-mall 接口文档",
                "Spring Boot 集成 Swagger3 测试接口文档",
                "v1.0",
                "https://cunyu1943.github.io",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                Lists.newArrayList()
        );
    }

}
