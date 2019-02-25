package com.example.demo.aop;

/**
 * @author admin
 * @date 2018-10-16 10:29
 */
public class TimeHandler {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void PrintTime(){
        System.out.println("Time "+System.currentTimeMillis());
    }


}
