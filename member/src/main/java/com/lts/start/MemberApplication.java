package com.lts.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.lts.start")
@MapperScan("com/lts/start/dao")
@ComponentScan(basePackages = "com.lts.start.config")
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class,args);
    }

}
