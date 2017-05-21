package cn.kevin.service.impl;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by yongkang.zhang on 2017/5/19.
 */
public class PasswordEncoderTest {

    @Test
    public void test() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String kevin = passwordEncoder.encode("123");
        boolean kevin1 = passwordEncoder.matches("123", "$2a$10$tl6TUKhTz7wSsFNJRql32uUqwsAcHI8eEEotZpyOJZce.6Cs3.eS6");
        System.out.println(kevin + "  : " + kevin1);
    }


}
