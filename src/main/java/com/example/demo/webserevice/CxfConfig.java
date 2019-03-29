package com.example.demo.webserevice;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @author admin
 * @date 2019-3-14 15:12
 */
@Configuration
public class CxfConfig {
    @Bean
    public ServletRegistrationBean didpatcherServlet(){
        return new ServletRegistrationBean(new CXFServlet(),"/demo/*");
    }

    @Bean(name= Bus.DEFAULT_BUS_ID)
    public SpringBus springBus(){
        return new SpringBus();
    }

    @Bean
    public DemoService demoService(){
        return new DemoServiceImpl();
    }

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(springBus(),demoService());
        endpoint.publish("/api");
        return endpoint;
    }


















}
