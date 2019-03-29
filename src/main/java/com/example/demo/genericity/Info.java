package com.example.demo.genericity;

/**
 * @author admin
 * TODO  单类型参数的泛型类
 * @date 2019-3-22 15:11
 */
public class Info<T> {
    private T value;

    public Info() {
    }

    public Info(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Info{" + "Value=" + value + "}";
    }


    //TODO 多个类型参数的泛型类
    static class MyMap<K, V> {
        private K key;
        private V value;

        public MyMap(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return "MyMap{" + "key=" + key + ", value=" + value + '}';
        }
    }

    static class Message<T> {
        private T value;

        public Message(T value) {
            this.value = value;
        }
    }

    //TODO 泛型接口
    public interface Content<T> {
        T text();
    }

    //第一种实现方式，实现接口的子类明确声明泛型类型
    class GenericsInterfaceDemo01 implements Content<Integer> {
        private int text;

        public GenericsInterfaceDemo01(int text) {
            this.text = text;
        }

        public Integer text() {
            return text;
        }
    }

    //第二种事项方式,实现接口的子类不明确声明泛型类型
    class GenericsInterfaceDemo02<T> implements Content<T> {
        private T text;

        public GenericsInterfaceDemo02(T text) {
            this.text = text;
        }

        @Override
        public T text() {
            return text;
        }
    }

    //TODO 泛型方法
    class GenericsMethodDemo01{
        public   <T> void printClass(T obj){
            System.out.println(obj.getClass().toString());
        }

    }


    public static void main(String[] args) {
        //TODO 泛型类的类型嵌套
        Message<String> info = new Message("Hello");
        MyMap<Integer, Message<String>> map = new MyMap<>(1, info);
        System.out.println(map);
    }


}
