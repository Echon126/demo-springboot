package com.example.demo.test;

import com.sun.javaws.jnl.PropertyDesc;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author admin
 * @date 2018-11-14 17:27
 */
public class TestMain {

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        method2();
    }
    //通过PropertyDescriptor创建属性的描述器
    public static  void method1() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Student st = new Student();
        String propertyName = "age";
        //1.通过构造器来创建PropertyDescriptor对象
        PropertyDescriptor pd = new PropertyDescriptor("name", Student.class);
        PropertyDescriptor pd2 = new PropertyDescriptor("age", Student.class);
        //2.通过该对象来获得写方法
        Method method1 = pd.getReadMethod();
        Method method2 = pd2.getReadMethod();
        //3.执行写方法
        Object object = method1.invoke(st);
        Object object2 = method2.invoke(st);
        //4.输出对象字段的值
        System.out.println(object);
        System.out.println(object2);
    }


    public static void method2() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Student student = new Student(1,"sss");
        BeanInfo bif = Introspector.getBeanInfo(Student.class);
        PropertyDescriptor pds[] = bif.getPropertyDescriptors();

        for(PropertyDescriptor  pd: pds){
            System.out.println(pd.getName());
            System.out.println(pd.getPropertyType());

            if(pd.getName().equals("name")){
                Method md = pd.getWriteMethod();
                md.invoke(student,"sssss");
            }
        }
        System.out.println(student.getName());
    }
}















