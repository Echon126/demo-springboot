package com.example.demo.utils.cas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
     */

    public static void main111(String[] args) {
        BlockingQueue<Integer> orderQueue = new LinkedBlockingQueue<>();
        Seller seller = new Seller(orderQueue);
        Thread[] sellerThread = new Thread[100];
        for (int i = 0; i < 100; i++) {
            sellerThread[i] = new Thread(seller);
            sellerThread[i].start();
        }

        Buyer buyer = new Buyer(orderQueue);
        Thread[] buyserThread = new Thread[100];
        for (int i = 0; i < 100; i++) {
            buyserThread[i] = new Thread(buyer);
            buyserThread[i].start();
        }

        try {
            //TODO   一旦创建生产者和消费者线程，他们会永远保持运行，将订单放入队列以及从队列中获取订单
            //TODO   根据给定时间的负载情况，定期自我阻塞，终止应用程序的方法是用户在键盘上按下Enter键。

            while (System.in.read() != '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
        //6、main函数会中断所有正在运行的生产者和消费者线程，要求它们中指并退出
        System.out.println("Terminating");
        for (Thread t : sellerThread) {
            t.interrupt();
        }
        for (Thread t : buyserThread) {
            t.interrupt();
        }
    }

    public static void main(String[] args) {
        List l = new ArrayList();
        System.out.println(l.get(0));
    }

}

















