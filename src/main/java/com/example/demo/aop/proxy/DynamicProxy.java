package com.example.demo.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author admin
 * @date 2019-1-24 11:58
 */
public class DynamicProxy  implements InvocationHandler {
    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=  method.invoke(object,args);
        return result;
    }
}
