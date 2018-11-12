package com.example.demo.data;

import java.util.Random;

/**
 * @author admin
 * @date 2018-9-26 16:55
 */
public class InternalClass {

    public InternalClass() {
    }

    protected  static class DispatcherServletConfiguration{
       static{
           System.out.println("static code ");
       }

        public static void out(){
            System.out.println("neibu class");
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt());
        System.out.println(random.nextInt(
                10
        ));
    }

}
