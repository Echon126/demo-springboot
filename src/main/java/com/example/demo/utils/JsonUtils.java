package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.*;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 封装了FastJson的方法.<br/>
 * 注: BaseAction对其有引用
 * @author LCC
 * @Date 2014年8月1日
 */
public class JsonUtils {
    /**
     * 将一个对象转化为Json字符串<br/>
     * 如果包含日期对象, 格式将转为 yyyy-MM-dd HH:mm:ss
     * @author LCC
     * @Date 2014年8月1日
     * @param object
     * @return Json字符串
     */
    public static String toString(Object object) {
    	return toString(object, "yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 将java对象转换成json字符串<br/>
     * 如果对象包含日期属性，可以指定日期格式，不包含传null <br/>
     * 默认日期格式为：yyyy-MM-dd HH:mm:ss
     * 
     * @param object 任意类型，包括集合
     * @param dateFormat 日期格式
     * @param features	特性配置
     * @return Json字符串
     */
    public static String toString(Object object, String dateFormat, SerializerFeature... features) {
    	return toStringWithFilter(object, dateFormat, null, null, null, features);
    }
    
    /**
     * 将java对象转换成json字符串（过滤指定属性）（如果指定的日期属性包含日期，可以指定日期格式，不包含传null，默认日期格式为：yyyy-MM-dd HH:mm:ss）
     * @param object　待转换的java对象
     * @param filterProperties　需要过滤的属性
     * @param isInclude 是否含指定属性
     * @param dateFormat
     * @param features
     * @return
     */
    public static String toStringFilterProperties(Object object, final String[] filterProperties, final boolean isInclude, String dateFormat, SerializerFeature... features) {
    	PropertyFilter propertyFilter = filterProperties(filterProperties, isInclude);
    	return toStringWithFilter(object, dateFormat, propertyFilter, null, null, features);
    }
    
    /**
     * 生成json字符串，使用指定过滤器
     * @param object
     * @param dateFormat
     * @param propertyFilter 属性过滤器
     * @param valueFilter 值过滤器
     * @param nameFilter 属性名称过滤器
     * @param features 特性设置
     * @return
     */
    public static String toStringWithFilter(Object object, String dateFormat, PropertyFilter propertyFilter, ValueFilter valueFilter, NameFilter nameFilter, SerializerFeature... features) {
    	SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (com.alibaba.fastjson.serializer.SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            if (propertyFilter != null) {
            	serializer.getPropertyFilters().add(propertyFilter);
            }
            if (valueFilter != null) {
            	serializer.getValueFilters().add(valueFilter);
            }
            if (nameFilter != null) {
            	serializer.getNameFilters().add(nameFilter);
            }
            if (!StringUtils.isEmpty(dateFormat)) {
            	serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
                serializer.setDateFormat(dateFormat);
            }
            serializer.write(object);
            return out.toString();
        } finally {
            out.close();
        }
    }
    /**
     * 将json字符串转换成指定javaBean，若json字符串中包含日期，则日期格式必须为yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
     * @param <T>
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz, Feature... features) {
    	T value = JSON.parseObject(json, clazz, features);
    	return value;
    }
    /**
     * 将json字符串转换成指定list，若json字符串中包含日期，则日期格式必须为yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
     * @param <T>
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
    	return JSON.parseArray(json, clazz);
    }
    /**
     * 用于重命名属性
     * @param renameMap key : 原始属性名　　value : 重命名后的属性名
     * @return
     */
    public static NameFilter renameProperty(final Map<String, String> renameMap) {
    	NameFilter nameFilter = new NameFilter() {
		    public String process(Object source, String name, Object value) {
		    	if (renameMap.containsKey(name)) {
		    		return renameMap.get(name);
		    	}
		        return name;
		    }
		};
    	return nameFilter;
    }
    /**
     * 用于过滤属性，只包含指定属性
     * @param properties 属性数组
     * @param isInclude 是否包含这些属性
     * @return
     */
    public static PropertyFilter filterProperties(final String[] properties, final boolean isInclude) {
    	PropertyFilter propertyFilter = new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (isInclude) {
					return Arrays.asList(properties).contains(name);
				} else {
					return !Arrays.asList(properties).contains(name);
				}
			}
        };
    	return propertyFilter;
    }
}