package org.chenzx.island;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description Spring Boot 启动类
 * @date 2022/7/9 12:35
 */
@SpringBootApplication
public class IslandApplication {

    public static void main(String[] args) {
        SpringApplication.run(IslandApplication.class, args);
    }

    /**
     * 加密算法
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
