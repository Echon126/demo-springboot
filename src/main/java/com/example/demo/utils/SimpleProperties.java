package com.example.demo.utils;


import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 因为Properties默认获得的是ISO8859-1编码, 所以用工具类给其转一下.
 *
 * @author LCC
 * @Date 2014年7月23日
 */
public class SimpleProperties extends Properties {
    private static final long serialVersionUID = 1278442039051714804L;

    /**
     * 获取指定的键值
     *
     * @param key
     * @return
     * @author LCC
     * @Date 2014年7月23日
     */
    public String getValue(String key) {
        return getValue(key, null);
    }

    /**
     * 获取指定的键值
     *
     * @param key
     * @param defaultValue 如果获取的数据不存在, 则要返回的数据.
     * @return
     * @author LCC
     * @Date 2014年7月23日
     */
    public String getValue(String key, String defaultValue) {
        return getValue(key, defaultValue, null);
    }

    /**
     * 获取指定的键值
     *
     * @param key
     * @param defaultValue 如果获取的数据不存在, 则要返回的数据.
     * @param encode       返回数据的编码, 默认是UTF-8
     * @return
     * @author LCC
     * @Date 2014年7月23日
     */
    public String getValue(String key, String defaultValue, String encode) {
        String v = this.getProperty(key, defaultValue);
        if (!StringUtils.isEmpty(v)) {
            encode = encode == null ? "UTF-8" : encode;
            try {
                return new String(v.getBytes("ISO8859-1"), encode);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }

        return v;
    }

    /**
     * 传入一组key, 依次读取, 直到读出的数据有值为止.
     *
     * @param keys
     * @return
     * @author LCC
     * @Date 2014年7月23日
     */
    public String queue(String... keys) {
        return queue(null, keys);
    }

    /**
     * 传入一组key, 依次读取, 直到读出的数据有值为止.
     *
     * @param defaultValue
     * @param keys
     * @return
     * @author LCC
     * @Date 2014年7月23日
     */
    public String queue(String defaultValue, String... keys) {
        return queue(defaultValue, null, keys);
    }

    /**
     * 传入一组key, 依次读取, 直到读出的数据有值为止.
     *
     * @param defaultValue
     * @param encode
     * @param keys
     * @return
     * @author LCC
     * @Date 2014年7月23日
     */
    public String queue(String defaultValue, String encode, String... keys) {
        for (String key : keys) {
            String v = this.getValue(key, null, encode);
            if (!StringUtils.isEmpty(v)) {
                return v;
            }
        }

        return defaultValue;
    }
}
