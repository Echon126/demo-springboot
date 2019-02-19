package com.example.demo.utils;



import com.example.demo.entity.DataMap;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类型转换工具类<br>
 * ---------------------------------------------<br>
 * <pre>
Object o = new Object[]{"1","2"};
Integer[] is = (Integer[]) ConvertTypeUtil.converToArray("int", o);
System.out.println(is[0]);
 * </pre>
 * 
 * @author LCC
 * @Date 2014年8月11日
 */
public class ConvertTypeUtil {
	public static Object converter(String type, Object o) throws ParseException {
		if(o==null){return o;}
		String value = ValueUtil.trimValue(o);
		type = type == null ? "" : type.trim().toLowerCase();
		
		boolean b1 = StringUtils.isEmpty(value);	// 如果是空的 或者是空字符串
		boolean b2 = "".equals(type) || "string".equals(type); // 如果是字符串类型的 或者没有定义类型
		// 如果没有值, 且也没有类型, 就当作是字符串处理 , 但是有类型存在, 就返回一个null
		if(b1){ return b2 ? "" : null; }
		
		try{
			switch (type) {
				case "int":
					return NumberUtils.toInt(value);
				case "double":
					return NumberUtils.toDouble(value);
				case "long":
					return NumberUtils.toLong(value);
				case "date":
					return DateUtils.parseDate(value, "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss");
				case "string":
					return value.toString();
				case "boolean":
					return Boolean.valueOf(value);
			}
		}catch(Exception e){
			return value;
		}
		
		return value;
	}
	public static Object[] converToArray(String type, Object o) throws ParseException {
		if(o == null || !o.getClass().isArray()){return null;}
		
		Object[] oo = (Object[]) o;
		Object[] os = makeEmtyArray(type, ((Object[]) o).length);
		
		for (int i=0; i<os.length; i++) {
			os[i] = converter(type, oo[i]);
		}
		
		return os;
	}
	/**
	 * 根据类型返回不同的数组
	 * 
	 * @author LCC
	 * @date 2014年8月19日
	 * @param type
	 * @param length
	 * @return
	 */
	public static Object[] makeEmtyArray(String type, int length){
		switch (type.trim().toLowerCase()) {
			case "int":
				return new Integer[length];
			case "double":
				return new Double[length];
			case "long":
				return new Long[length];
			case "date":
				return new Date[length];
		}
		return new Object[length];
	}
	
	
	public static List<DataMap> dataMapStringTypeKeytoList(DataMap dm, String idName, String keyName){
		List<DataMap> resultList = new ArrayList<DataMap>();
		Object[] list  =(Object[]) dm.get(idName);
		for(Object ss:list){
			DataMap keyMap = new DataMap();
			keyMap.put(keyName, ss.toString());
			resultList.add(keyMap);
		}
		return resultList;
		
	}
}  
