package com.example.demo.annotationeaxmple;

import com.example.demo.annotationeaxmple.impl.SimpleContentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2018-11-13 10:22
 */
@Configuration
public class ContentConfiguration {
    public ContentConfiguration() {
        System.out.println("-----------------");
    }
    @Bean
    public ContentService contentService(){
        return new SimpleContentService();
    }
}
