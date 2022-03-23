package com.lts.member;

import com.lts.start.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MemberApplicationTests.class)
class MemberApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(JWTUtils.acquireJWT("测试","qwerty"));
    }

    @Test
    public void test(){
        String token=JWTUtils.acquireJWT("测试","qwerty");

        System.out.println(JWTUtils.parseJWT(token));

    }

}
