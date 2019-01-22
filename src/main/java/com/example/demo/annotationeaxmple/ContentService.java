package com.example.demo.annotationeaxmple;

/**
 * @author admin
 * @date 2018-11-13 10:15
 */

/**
 * 需求：定义一个Annotation，让使用这个Annotation的应用程序自动化的注入一些类或者做一些底层的事情
 *
 * 实现逻辑，使用Spring的@Import配合一个配置类完成
 */

public interface ContentService {
    void doSomething();
}
