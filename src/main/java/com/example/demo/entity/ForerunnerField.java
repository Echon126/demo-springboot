package com.example.demo.entity;


/**
 * 配置数据的字段<br>
 * 包括了中文名称, 验证等
 * @author LCC
 * @Date 2014年8月9日
 */
public class ForerunnerField {
	/**
	 * 变量名 
	 */
	private String name;
	/**
	 * 字段类型
	 */
	private String type;
	/**
	 * 别名, 中文名称 
	 */
	private String alias;
	/**
	 * 权限控制域
	 */
	private String domain;
	/**
	 * 内容过滤
	 */
	private String filter;
	/**
	 * 字典
	 */
	private String dictionary;
	
	/**
	 * 导出时的排序
	 */
	private Integer exSort;
	/**
	 * 导出时的别名
	 */
	private String exAlias;
	/**
	 * 导入是的排序
	 */
	private Integer imSort;
	/**
	 * 字段校验
	 */
	private ForerunnerValidate validate;
	/**
	 * 外键关联
	 */
	private ForerunnerLink link;
	/**
	 * 该字段的信息来自于外键关联<br>
	 * 冗余字段
	 */
	private boolean isFkField = false;
	
	/// Getter And Setter ///
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ForerunnerValidate getValidate() {
		return validate;
	}
	public void setValidate(ForerunnerValidate validate) {
		this.validate = validate;
	}
	public ForerunnerLink getLink() {
		return link;
	}
	public void setLink(ForerunnerLink link) {
		this.link = link;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDictionary() {
		return dictionary;
	}
	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}
	public boolean isFkField() {
		return isFkField;
	}
	public void setFkField(boolean isFkField) {
		this.isFkField = isFkField;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public Integer getExSort() {
		return exSort;
	}
	public void setExSort(Integer exSort) {
		this.exSort = exSort;
	}
	public String getExAlias() {
		return exAlias;
	}
	public void setExAlias(String exAlias) {
		this.exAlias = exAlias;
	}
	public Integer getImSort() {
		return imSort;
	}
	public void setImSort(Integer imSort) {
		this.imSort = imSort;
	}
	
}
