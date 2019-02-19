package com.example.demo.entity;



import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 外键关联
 * @author LCC
 * @Date 2014年8月9日
 */
public class ForerunnerLink {
	/**
	 * 这个属性是用来标记外键要显示的名称.<br>
	 * 例如学生的班级ID. 要从班级中的哪个字段上取名称.默认从班级的下拉列表的value字段取名.
	 */
	private String field;
	/**
	 * 关联表的Forerunner配置的文件名
	 */
	private String value;
	/**
	 * 下拉列表的地址
	 */
	private String url;
	/**
	 * 这个是通过field在配置文件中获取到的
	 */
	private String alias;
	
	/// Methods ///
	
	public void setAlias() {
		ForerunnerConfig config = ForerunnerCache.getConfig(this.value);
		if(config == null){return;}
		Map<String, ForerunnerField> fields = config.getFields();
		if(fields == null){return;}
		
		String ftem = null;
		if(StringUtils.isEmpty(this.field)){
			if(config.getNode() == null || config.getNode().getSelect() == null){
				throw new RuntimeException("Forerunner配置文件不完整...");
			}else{
				ftem = config.getNode().getSelect().getValue();
			}
		}else{
			ftem = this.field;
		}
		
		ForerunnerField f = fields.get(ftem);
		if(f == null){return;}
		this.alias = f.getAlias();
	}
	
	/// Getter And Setter ///
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAlias() {
		return alias;
	}
}
