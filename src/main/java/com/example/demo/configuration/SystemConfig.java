package com.example.demo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author admin
 * @date 2019-2-18 11:06
 */
@Component
public class SystemConfig {
    private final static Logger logger = LoggerFactory.getLogger(SystemConfig.class);
    private final static String SYSTEM_CONFIG = "forerunner";

    @PostConstruct
    public void init() {
        String path;
        try {
            path = this.getClass().getClassLoader().getResource("").toURI().getPath();
            logger.info("初始化配置文件是的读取路径：{}" + path);
            ForerunnerCacheManager.init(path + SYSTEM_CONFIG);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化Forerunner配置文件出错. 错误信息: {}", e.getMessage());
        }
    }

}
