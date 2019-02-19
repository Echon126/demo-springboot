package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 将Forerunner返回的数据格式进行处理为所需的数据格式<br>
 * 可以集成后实现自己的转换方式
 * 
 * @author LCC
 * @date 2014年8月13日
 */
public abstract class ConverterFactory {
	/**
	 * 显示的文本不需要按照 id-name组装
	 */
	private Boolean justName = false;
	/**
	 * 清除多余的字段
	 */
	private boolean clearUnnecessary = false;
	/**
	 * 当前的节点信息
	 */
	protected ForerunnerNode node;
	protected final List<DataMap> emptyDML = new ArrayList<>();
	
	/**
	 * 数据转换
	 * 
	 * @author LCC
	 * @date 2014年8月19日
	 * @param dl 原始数据
	 * @param rdl 返回数据
	 * @param ForerunnerConfig cache 配置的缓存
	 */
	public abstract List<DataMap> toConverter(List<DataMap> dl);
	/**
	 * 进行数据转换
	 * 
	 * @author LCC
	 * @date 2014年8月19日
	 * @param DataMap dm 
	 */
	public final List<DataMap> doConverter(List<DataMap> dl, String cache){
		if(dl == null || dl.size() == 0 || cache == null){return emptyDML;}
		
		node = ForerunnerCache.getConfig(cache).getNode();
		if(node == null){return emptyDML;}
		
		return toConverter(dl);
	}
	
	/// Getter And Setter ///
	public boolean isClearUnnecessary() {
		return clearUnnecessary;
	}
	public void setClearUnnecessary(boolean clearUnnecessary) {
		this.clearUnnecessary = clearUnnecessary;
	}
	public Boolean getJustName() {
		return justName;
	}
	public void setJustName(Boolean justName) {
		this.justName = justName;
	}
}
