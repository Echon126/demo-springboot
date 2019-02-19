package com.example.demo.entity;
/**
 * 对字段的校验
 * @author LCC
 * @Date 2014年8月9日
 */
public class ForerunnerValidate {
	/**
	 * 非空
	 */
	private Boolean required;
	/**
	 * 最大值[包含]
	 */
	private String max;
	/**
	 * 最小值[包含]
	 */
	private String min;
	/**
	 * 正则表达式
	 */
	private String custom;
	
	/// Getter And Setter ///
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
}
