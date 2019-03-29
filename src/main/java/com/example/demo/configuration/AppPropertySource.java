package com.example.demo.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

/**
 * @author admin
 * @date 2019-3-4 11:49
 */
@Configuration
@PropertySource(value = "classpath:/systemConfig.properties")
public class AppPropertySource {

    @Bean
    public PropertySourcesPlaceholderConfigurer  propertyConfiguration(){
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    @Scope("prorotype")
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}

