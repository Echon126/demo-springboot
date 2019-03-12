package com.example.demo.utils.cas;

/**
 * @author admin
 * @date 2019-3-7 10:34
 */
public class TheadLocalDemo {
    /** ThreadLocal 是一个本地线程副本变量工具类，主要用于将私有线程和该线程存放的副本对象做一个映射，各个线程之间的变量互不干扰。
     *  在高并发的情况下，可以实现无状态的调用，使用与各个线程不共享变量值的操作。
     *
     *
     * 工作原理：
     * 每个线程内部维护了一个ThreadLocalMap，它是一个Map数据格式，key是一个弱引用，也就是ThreadLocal本身，而value存的是线程变量的值
     * 也就说，ThreadLocal本身并不存储线程变量的值，他只是一个工具，用来维护内部Map，帮助存和取变量。
     *
     *
     *
     *
     *
     */
}

















