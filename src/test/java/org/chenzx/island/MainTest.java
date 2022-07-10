package org.chenzx.island;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description main test
 * @date 2022/7/10 14:43
 */
@SpringBootTest
public class MainTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testGetPassword() {
        String password = "123456";
        String encode = passwordEncoder.encode(password);
        System.out.println(encode);
    }

}
