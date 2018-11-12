package com.example.demo.asm.jdk;

/**
 * @author admin
 * @date 2018-10-16 9:54
 */
public class ServiceImpl implements Service ,Item {
    public ServiceImpl() {
        super();
    }

    @Override
    public void test() {
        System.out.println("Test dynamic proxy");
    }

    @Override
    public void help() {
        System.out.println("I want to buy some book");
    }

    @Override
    public void hello(String str) {
        System.out.println("hello: "+str);
    }
}
