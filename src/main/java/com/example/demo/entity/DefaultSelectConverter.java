package com.example.demo.entity;

import java.util.List;

/**
 * 将数据转换为下拉列表数据的默认实现
 * 
 * @author LCC
 * @date 2014年8月13日
 */
public class DefaultSelectConverter extends ConverterFactory{
	@Override
	public List<DataMap> toConverter(List<DataMap> dl) {
		// 获得Key和Value对应的字段
		ForerunnerSelect sfs = node.getSelect();
		// 如果不存在配置, 就只能返回原有的数据
		if(sfs == null){return dl;}
		
		for (DataMap dm : dl) {
			// 需要使用getDataMap(dm)方法来获取DM. 因为不然得需要自己判断用户是否保留其他字段
			Object key = dm.get(sfs.getKey());
			Object value = dm.get(sfs.getValue());
			
			dm.clear(isClearUnnecessary());
			
			dm.put("key", key);
			
			if(this.getJustName()){
				dm.put("value", value);
			}else{
				dm.put("value", value + " - " + key);
			}
		}
		
		return dl;
	}
}
