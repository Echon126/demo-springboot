package com.example.demo.test;

/**
 * @author admin
 * <p>
 * Java内省机制
 * 内省机制是Java语言对JavaBean类属性、事件的一种缺省处理方法。
 * JavaBean是一种特殊的类，主要用于传递数据信息，
 * 这种数据信息主要用于防范私有的字段、且方法名符合某种特殊命名。
 *
 * 如果两个模块之间传递信息，可以将信息封装到JavaBean中，这种对象称为值对象或“VO”，方法比较少，
 * 这些信息存储在类的私有变量中，通过set() 、get()方法获取、。
 *
 * Java内省库
 * ProperDescriptor
 *  该类表示JavaBean类通过存储器导出一个属性，主要方法是：
 *  1.getPropertyType()，获得属性的class对象
 *  2.getReadMethod 获得用于读取属性值的方法，getWriteMethod()，获得用于写入属性值的方法;
 *  3.hashCode()，获取对象的哈希值;
 *  4.setReadMethod(Method readMethod)，设置用于读取属性值的方法;
 *  5.setWriteMethod(Method writeMethod)，设置用于写入属性值的方法。
 *
 * @date 2018-11-14 17:17
 */
public class Student {
    private int age;
    private String name;

    public Student(){
        System.out.println("init bean ......");
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
