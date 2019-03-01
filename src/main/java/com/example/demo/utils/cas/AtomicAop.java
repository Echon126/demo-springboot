package com.example.demo.utils.cas;

import sun.misc.Unsafe;

import java.util.StringJoiner;

/**
 * AtomicInteger 源码分析
 *
 * @author admin
 * @date 2019-2-26 18:01
 */
public class AtomicAop   {
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static   long valueOffset;
    
    static{
        try {
            valueOffset = unsafe.objectFieldOffset
                    (AtomicAop.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private volatile int value;    //保证了可见性和有序性

    /**
     * get操作不是加锁的，对于非 volatile 类型的共享变量，并发操作时，
     * 一个读线程未必能立马读取到其他线程对这个共享变量的修改，但是volatile 的变量，
     * 可以立马看到其他线程对该变量的修改。
     *
     * @return
     */
    public final int get(){
        return value;
    }


    public final int incrementAndGet(){
        return unsafe.getAndAddInt(this, valueOffset, 1)+1;
    }

    public static void main(String[] args) {
        StringJoiner sj = new StringJoiner("hoeee");

    }


}












