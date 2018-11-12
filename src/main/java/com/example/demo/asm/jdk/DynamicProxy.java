package com.example.demo.asm.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author admin
 * @date 2018-10-16 9:56
 */
public class DynamicProxy implements InvocationHandler {
    private Object service;

    public DynamicProxy(Object service){
        this.service=service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before buy some book");
        System.out.println("method: "+method);

        method.invoke(service,args);
        System.out.println("after buy some book");
        return null;
    }
}
