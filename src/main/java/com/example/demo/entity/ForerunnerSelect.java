package com.example.demo.entity;
/**
 * 下拉列表对应的字段
 * @author LCC
 * @Date 2014年8月9日
 */
public class ForerunnerSelect {
	private String key;
	private String value;
	
	public ForerunnerSelect() { }
	public ForerunnerSelect(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/// Getter And Setter ///
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
