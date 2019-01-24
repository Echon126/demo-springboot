package com.example.demo.aop.proxy.impl;

import com.example.demo.aop.proxy.Subject;

/**
 * @author admin
 * @date 2019-1-24 11:45
 */
public class RealSubject implements Subject {
    private String name="zhangsan";
    @Override
    public void visit() {
        System.out.println(name);
    }
}
