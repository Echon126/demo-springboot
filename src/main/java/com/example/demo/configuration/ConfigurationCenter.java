package com.example.demo.configuration;

import com.wen.excel.configuration.BuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author admin
 * @date 2018-11-13 10:02
 */
@Configuration
public class ConfigurationCenter {
    @Autowired
    ApplicationConfiguration app;

    @Bean
    public BuilderConfiguration configuration() throws FileNotFoundException {
        File sourceFile = ResourceUtils.getFile(app.getModelUrl());
        return new BuilderConfiguration(app.getDownDir(), sourceFile.getAbsolutePath());
    }
}
