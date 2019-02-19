package com.example.demo.entity;

import com.example.demo.utils.SimpleProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 缓存Forerunner配置
 *
 * @author LCC
 * @Date 2014年8月9日
 */
public class ForerunnerCache {
    /**
     * 字段配置缓存
     */
    private static Map<String, ForerunnerConfig> config = new HashMap<>();
    /**
     * 异常信息配置
     */
    private final static SimpleProperties esp = new SimpleProperties();

    /**
     * 获取缓存
     *
     * @param key
     * @return ForerunnerConfig
     * @author LCC
     * @Date 2014年8月9日
     */
    public static ForerunnerConfig getConfig(String key) {
        return config.get(key);
    }

    /**
     * 添加缓存
     *
     * @param key
     * @param fc
     * @author LCC
     * @Date 2014年8月9日
     */
    public static void addConfig(String key, ForerunnerConfig fc) {
        config.put(key, fc);
    }

    /**
     * 获取所有的配置文件的Key
     *
     * @return
     * @author LCC
     * @date 2014年9月19日
     */
    public static Set<String> getKeySet() {
        return config.keySet();
    }

    /**
     * 获取异常的错误信息
     *
     * @param key
     * @return
     * @author LCC
     * @date 2014年8月27日
     */
    public static String getExceptionInfo(String key) {
        // 如果配置了自定义异常
        if (esp.containsKey(key)) {
            return esp.getValue(key);
        }

        String[] tem = key.split("\\.");
        key = "commons." + tem[tem.length - 1];
        // 如果找到了通用异常
        if (esp.containsKey(key)) {
            return esp.getValue(key);
        }
        // 如果什么也没有找到
        return esp.getValue("commons.x");
    }

    /**
     * 添加信息
     *
     * @throws IOException
     * @throws
     * @author LCC
     * @date 2014年8月27日
     */
    public static SimpleProperties initExceptionInfo(File f) throws IOException {
        esp.load(new FileInputStream(f));

        return esp;
    }
}
