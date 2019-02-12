package com.example.demo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author admin
 * @date 2019-1-29 9:57
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello").setViewName("success");
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList() {
            {
                add(12);
                add(20);
                add(12);
                add(22);
                add(22);
                add(23);
                add(159);
                add(12);
                add(12);
                add(12);
                add(12);
                add(12);
                add(12);
                add(12);
                add(12);
                add(12);
                add(12);
                add(13);
            }
        };
        Map<Integer, Long> map = list.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        map.forEach((k, v) -> System.out.println(k + ":" + v));


        System.out.println(ThreadLocalRandom.current().nextInt());


    }
}
