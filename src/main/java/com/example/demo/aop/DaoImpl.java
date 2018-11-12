package com.example.demo.aop;

/**
 * @author admin
 * @date 2018-10-16 10:28
 */
public class DaoImpl implements Dao{
    @Override
    public void select() {
        System.out.println("select data");
    }

    @Override
    public void insert() {
        System.out.println("insert data");
    }
}
