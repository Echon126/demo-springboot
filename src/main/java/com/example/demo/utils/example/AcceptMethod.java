package com.example.demo.utils.example;

import java.util.Arrays;
import java.util.List;

/**
 * @author admin
 * @date 2019-3-4 11:38
 */
public class AcceptMethod {
    public static void printValue(String str) {
        System.out.println("prinlt value : " + str);
    }

    public static void main(String[] args) {
        List<String> al = Arrays.asList("a", "b", "c", "d");
        for (String a : al) {
            printValue(a);
        }
        al.forEach(x -> {
            AcceptMethod.printValue(x);
        });
    }
}
