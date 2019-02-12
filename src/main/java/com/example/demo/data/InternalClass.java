package com.example.demo.data;

import org.apache.poi.poifs.crypt.HashAlgorithm;

import java.util.Random;

/**
 * @author admin
 * @date 2018-9-26 16:55
 */
public class InternalClass {

    public InternalClass() {
    }

    protected static class DispatcherServletConfiguration {
        static {
            System.out.println("static code ");
        }

        public static void out() {
            System.out.println("neibu class");
        }
    }

    public static void main(String[] args) {
        int sum = 0, n = 100;
        sum = (1 + n) * n / 2;
        System.out.println(sum);

    }

    private static void aynalize() {
        int[] array = {'A', 'A', 'A', 'C'};
        for (int i = 0; i < array.length; i++) {

        }


    }


}
