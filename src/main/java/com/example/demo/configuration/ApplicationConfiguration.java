package com.example.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author admin
 * @date 2019-2-19 11:02
 */
@Configuration
@ConfigurationProperties(value = "appconfig")
public class ApplicationConfiguration {
    private String downDir;
    private String modelUrl;

    public ApplicationConfiguration() throws FileNotFoundException {
    }

    public String getDownDir() {
        return downDir;
    }

    public void setDownDir(String downDir) {
        this.downDir = downDir;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
    }


}
