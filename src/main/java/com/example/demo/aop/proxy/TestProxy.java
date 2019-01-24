package com.example.demo.aop.proxy;

import com.example.demo.aop.proxy.impl.ProxySubject;
import com.example.demo.aop.proxy.impl.RealSubject;

import java.lang.reflect.Proxy;

/**
 * @author admin
 * @date 2019-1-24 11:47
 */
public class TestProxy {
    public static void main(String[] args) {
//        ProxySubject proxySubject = new ProxySubject(new RealSubject());
//        proxySubject.visit();
        RealSubject realSubject = new RealSubject();
        DynamicProxy proxy = new DynamicProxy(realSubject);
        ClassLoader classLoader = realSubject.getClass().getClassLoader();
        Subject subject = (Subject) Proxy.newProxyInstance(classLoader, new  Class[]{Subject.class}, proxy);
        subject.visit();
    }
}
