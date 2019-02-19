package com.example.demo.entity;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * BaseModel和DataMap互转
 * 
 * @author LCC
 * @date 2014年9月2日
 */
public class ConvertClass {
	/** 
     * 将一个 BaseModel 对象转化为一个  DataMap 
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */  
    public static <T extends DataModel> DataMap toDataMap(T t) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        DataMap dm = new DataMap();  
        Class<?> type = t.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
  
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
        	String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {  
                Method readMethod = descriptor.getReadMethod();
                if(readMethod == null){
                	continue;
                }
                Object result = readMethod.invoke(t, new Object[0]);
                if (result != null) {  
                    dm.put(propertyName, result);  
                }
            }  
		}
        
        dm.setName(t.getNamespace());
        dm.setUser(t.getCurrentUser());
        dm.setConverter(t.getConverter());
        dm.setConfig(t.getConfig());
        
        return dm;  
    }  
    /** 
     * 将一个 DataMap 对象转化为一个 BaseModel 
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InstantiationException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */  
    public static <T extends DataModel> T toModel(DataMap dm, Class<T> type) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
    	T t = type.newInstance(); // 创建 JavaBean 对象  
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
  
        // 给 JavaBean 对象的属性赋值  
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
        	String propertyName = descriptor.getName();
            if (dm.containsKey(propertyName) && dm.get(propertyName) != null) {  
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  
                Object value = dm.get(propertyName);
  
                Object[] args = new Object[1];
                args[0] = value;  
  
                descriptor.getWriteMethod().invoke(t, args);  
            }  
        }
        
        t.setConfig(dm.getConfig());
        t.setConverter(dm.getConverter());
        t.setCurrentUser(dm.getUser());
        t.setNamespace(dm.getName());
        
        return t;  
    }  
}
