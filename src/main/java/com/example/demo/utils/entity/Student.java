package com.example.demo.utils.entity;

import lombok.Data;

/**
 * @author admin
 * @date 2019-3-26 10:05
 */
@Data
public class Student {
    private Integer id;
    private String name;
    private Integer age;

    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
