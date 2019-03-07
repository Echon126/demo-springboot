package com.example.demo.utils.entity;

import java.util.*;

/**
 * @author admin
 * @date 2019-3-7 10:03
 */
public class People {
    private String name;
    private String phoneNumber;

    public People(String name, String phoneNumber) {
        super();
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return Objects.equals(name, people.name) &&
                Objects.equals(phoneNumber, people.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }

    public static void main(String[] args) {
        List<People> peopleList = Arrays.asList(
                new People("张三", "11111"),
                new People("张三", "22222"),
                new People("李四", "33333"),
                new People("张三", "22222"));

        Set<People> peopleSet = new HashSet<People>();
        peopleSet.addAll(peopleList);

        System.out.println("List " + peopleList.toString());
        System.out.println("set " + peopleSet.toString());
    }

    /**
     * TODO List 中元素去重， 1.对于List中存储的基本类型的数据，只需要将List 转化成Set 则可去重
     *                      2.List中存储的是Object时，需要重equals和hashCode方法
     *
     *
     * 原因：比较两个对象时，首先需要判断两个对象是否具有相同的地址，如果是同一个对象的引用，则返回true
     * 如果地址不一样，则证明不是引用同一个对象，接下来就是挨个去比较两个字符串对象的内容是否一致，
     * 完全相等返回true，否则false。
     */
}
