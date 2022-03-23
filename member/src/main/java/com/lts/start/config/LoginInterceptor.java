package com.lts.start.config;

import com.lts.start.entity.UmsEntity;
import com.lts.start.service.UmsService;
import com.lts.start.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired(required = false)
    private UmsService umsService;

    /**
     * 转发请求前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().contains("tologin")){
//            如果要去登录页面，那就不拦截
            System.out.println(request.getRequestURI());
            return true;
        }
        else{
            String token=request.getHeader("token");

            if(token!=null){
//                判断数据库是否存在对象（emmmmm这样对数据库的压力有点大）
                String openid=(String) JWTUtils.parseJWT(token).get("account");
                UmsEntity user=umsService.getById(openid);
                return user != null;
            }
            return false;
    }
    }

    /**
     * 处理请求之后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 整个请求结束之后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
