package com.example.demo.annotationeaxmple;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author admin
 * @date 2018-11-13 10:17
 */

//TODO 定义一个EnableContentService注解，使用了这个注解的程序会自动注入ContentService这个bean

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ContentConfiguration.class)
public @interface EnableContentService {
}
