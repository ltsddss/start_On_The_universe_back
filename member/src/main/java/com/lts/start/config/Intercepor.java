package com.lts.start.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置拦截器
 */
public class Intercepor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        获取携带的token

        System.out.println(request.getRequestURI());

        String token= request.getHeader("token");
//        如果heander中并没有携带token

        System.out.println(token);

        if(token==null|| token.equals("")){
//            跳转到前端登录页面
            response.sendError(400,"用户未登录请登录后再访问");
        }
        else {
//            携带了token，则根据token获取在redis中存储的用户权限的url，如果存在该路径则正常执行，如果没有该路径则跳到403页面
            /**
             * 向redis中查询时候又该为成员的权限数据,获取String类型的auths
             */
            String auth = redisTemplate.opsForValue().get(token);

            if(auth==null){
//                如果权限为空说明未登录
                response.sendError(400,"用户未登录");
            }
            else if(auth.contains(request.getRequestURI())){
//                如果权限路径中存在改路径则放行
                return true;
            }
            else {
//                如果没有改路径，则返会没有权限
                response.sendError(403,"没有该权限");
            }

        }
//        不符合上述条件的任何一种，返回false
        return false;
    }
}
