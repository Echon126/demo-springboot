package com.example.demo.utils;


/**
 * 值处理工具类
 * 
 * @author LCC
 * @date 2014年9月3日
 */
public class ValueUtil {
	public static String trimValue(Object o){
		return o == null ? null : o.toString().trim();
	}
}
