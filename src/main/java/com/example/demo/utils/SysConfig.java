package com.example.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * TODO  动态加载配置文件，同时检查文件是否被修改过
 *
 * @author admin
 * @date 2019-2-28 16:07
 */
public class SysConfig {
    private static Properties props = null;
    private static File configFile = null;
    private static long fileLastModified = 0L;

    private final static String CONFIG_FILENAME = "application.properties";

    private static void init() {
        URL url = SysConfig.class.getClassLoader().getResource(CONFIG_FILENAME);
        configFile = new File(url.getFile());
        props = new Properties();

    }

    private static void load() {
        props.clear();
        try {
            props.load(new InputStreamReader(new FileInputStream(configFile), "UTF-8"));
            fileLastModified = configFile.lastModified();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getConfig(String key) {
        if ((configFile == null) || (props == null)) {
            init();
        }

        //当检查到文件被修改是没重新加载文件
        if (configFile.lastModified() > fileLastModified) {
            load();
        }

        return props.getProperty(key);

    }

    public static void main(String[] args) {
        getConfig("ddddd");
    }


    //TODO PropertyPlaceholderConfigurer 属性配置器
    /**
     *
     * <bean id="propertyConfigurer"
     * 		class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
     * 		<property name="locations">
     * 			<list>
     * 				<value>classpath*:systemConfig.properties</value>
     * 			</list>
     * 		</property>
     * 	</bean>
     *
     *
     */

}
