package com.example.demo.container.inteceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author admin
 * @date 2019-2-26 10:33
 */
@Configuration
public class IntercepprotConfiguration  implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(new CustomeInterceptor()).addPathPatterns("/*");
    }

}
