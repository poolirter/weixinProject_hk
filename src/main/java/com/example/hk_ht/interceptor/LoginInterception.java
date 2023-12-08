package com.example.hk_ht.interceptor;

import com.alibaba.fastjson.JSON;

import com.example.hk_ht.Util.JwtUtil;
import com.example.hk_ht.Util.NoAuth;
import com.example.hk_ht.Util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class LoginInterception implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorize");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.hasMethodAnnotation(NoAuth.class)) {
            return true;
        }
        if (!jwtUtil.verifyToken(token)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString("token已经失效，请重新登录！！！"));
            return false;
        }
//        String idFromToken = jwtUtil.getIdFromToken(token);
//        if (!redisUtil.hasKey(idFromToken)) {
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write(JSON.toJSONString("未登录"));
//            return false;
//        }
        jwtUtil.refreshToken(token);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
