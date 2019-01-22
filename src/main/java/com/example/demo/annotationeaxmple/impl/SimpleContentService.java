package com.example.demo.annotationeaxmple.impl;

import com.example.demo.annotationeaxmple.ContentService;

/**
 * @author admin
 * @date 2018-11-13 10:20
 */
public class SimpleContentService implements ContentService {
    @Override
    public void doSomething() {
        System.out.println("do some simple things");
    }
}
