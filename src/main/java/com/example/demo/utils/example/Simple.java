package com.example.demo.utils.example;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @date 2019-3-12 14:32
 */
public class Simple {
    //TODO 可以动态加载配置文件，比较low的方式
    private Map<String, String> valueMap = new HashMap<String, String>();


    public String getValue(String key) {
        return this.valueMap.get(key);
    }


    public void setValueMap(String key, String value) {
        this.valueMap.put(key, value);
    }

}
