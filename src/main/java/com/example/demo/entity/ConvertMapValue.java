package com.example.demo.entity;

import com.example.demo.utils.ConvertTypeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * 将一个值按照调用的方法进行转换<br>
 * ----------------------------------------<br>
 * ConvertMapValue cmv = new ConvertMapValue(new Object[]{"1","2"});<br>
 * System.out.println(cmv.toDoubles()[0]);
 * 
 * @author LCC
 * @date 2014年8月20日
 */
public class ConvertMapValue {
	/**
	 * 值
	 */
	private Object value;
	
	public ConvertMapValue(Object value) {
		this.value = value;
	}
	
	/// Methods ///
	/**
	 * 转换为字符串
	 */
	public String toString() {
		return this.value==null ? null : this.value.toString();
	}
	/**
	 * 转换为整数
	 * 
	 * @author LCC
	 * @date 2014年8月20日
	 * @return
	 */
	public Integer toInt(){
		return NumberUtils.toInt(this.toString());
	}
	/**
	 * 转换为Long
	 * 
	 * @author LCC
	 * @date 2015年1月9日
	 * @return
	 */
	public Long toLong(){
		return NumberUtils.toLong(this.toString());
	}
	/**
	 * 转换为小数
	 * 
	 * @author LCC
	 * @date 2014年8月20日
	 * @return
	 */
	public Double toDouble(){
		return NumberUtils.toDouble(this.toString());
	}
	/**
	 * 转换为日期
	 * 
	 * @author LCC
	 * @date 2014年8月20日
	 * @return
	 */
	public Date toDate(){
		Object o = this.toObject("date");
		return (Date) (o == null ? null : o);
	}
	/**
	 * 转换为布尔值
	 * 
	 * @author LCC
	 * @date 2014年8月21日
	 * @return
	 */
	public Boolean toBoolean(){
		return Boolean.valueOf(this.toString());
	}
	/**
	 * 字符串数组
	 * 
	 * @author LCC
	 * @date 2014年9月2日
	 * @return
	 */
	public String[] toStrings(){
		Object[] objs = this.toObjects();
		
		if(objs != null){
			String[] strs = new String[objs.length];
			for (int i=0; i<objs.length; i++) {
				strs[i] = String.valueOf(objs[i]);
			}
			
			return strs;
		}
		
		return null;
	}
	/**
	 * 整形数组
	 * 
	 * @author LCC
	 * @date 2014年8月20日
	 * @return
	 */
	public Integer[] toInts(){
		Object[] o = this.toObjects("int");
		return  o == null ? null : (Integer[])o;
	}
	/**
	 * 小数数组
	 * 
	 * @author LCC
	 * @date 2014年8月20日
	 * @return
	 */
	public Double[] toDoubles(){
		Object[] o = this.toObjects("double");
		return o == null ? null : (Double[])o;
	}
	/**
	 * 转换为指定类型的Object
	 * 
	 * @author LCC
	 * @date 2014年8月20日
	 * @param type
	 * @return
	 */
	public Object toObject(){
		return this.value;
	}
	public Object toObject(String type){
		try {
			return ConvertTypeUtil.converter(type, value);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 转换为指定类型的对象数组
	 * 
	 * @author LCC
	 * @date 2014年8月20日
	 * @param type
	 * @return
	 */
	public Object[] toObjects(){
		if(this.value == null){
			return null;
		}
		
		if(this.value.getClass().isArray()){
			return (Object[]) this.value;
		}
		
		return new Object[]{this.value};
	}
	public Object[] toObjects(String type){
		try {
			return ConvertTypeUtil.converToArray(type, value);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 是否为空
	 * 
	 * @author LCC
	 * @date 2014年9月1日
	 * @return
	 */
	public boolean isNull(){
		return this.value == null;
	}
	public boolean isEmpty(){
		return StringUtils.isEmpty(isNull() ? null : value.toString());
	}
	/**
	 * 重写比较方法
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConvertMapValue){
			obj = obj == null ? null : obj.toString();
		}
		
		if(this.value == null && obj == null){return true;}
		
		if(this.value != null && obj != null){
			return this.value.toString().equals(obj.toString());
		}
		
		return false;
	}
	
	/// Getter And Setter ///
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
