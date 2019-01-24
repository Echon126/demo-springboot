package com.example.demo.aop.proxy.impl;

import com.example.demo.aop.proxy.Subject;

/**
 * @author admin
 * @date 2019-1-24 11:46
 */
public class ProxySubject implements Subject {
    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void visit() {
        subject.visit();
    }
}
