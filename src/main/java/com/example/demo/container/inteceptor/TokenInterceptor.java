package com.example.demo.container.inteceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author admin
 * @date 2019-2-26 10:30
 */
public class TokenInterceptor  extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("------------自定义拦截器-----------------");
        return true;
    }

/**
 * TODO  HandlerInterceptorAdapter 拦截器适配器， 如果直接实现 HandlerInterceptor 接口，则该接口下定义的所有方法都需要实现，
 * 通过适配类可以方便使用，根据自己的实际情况实现相应的方法。
 * 拦截器的基本使用和架构说明  https://www.jianshu.com/p/1e8d088c2be9
 *
 *
 *
 *
 */
}

