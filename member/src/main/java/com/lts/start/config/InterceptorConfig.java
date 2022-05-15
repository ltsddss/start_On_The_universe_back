package com.lts.start.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//定义此类为配置类
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

/**
 * 需要放行的url:{
 *      注册，
 *      登录
 * }
 */
        String[] excludePathPatterns = {
                "/outworld_member/member/login",
                "/outworld_member/member/register"
        };
        registry.addInterceptor(getIntercepor()).excludePathPatterns(excludePathPatterns);
    }
    @Bean
    public Intercepor getIntercepor(){
        return new Intercepor();
    }
}

