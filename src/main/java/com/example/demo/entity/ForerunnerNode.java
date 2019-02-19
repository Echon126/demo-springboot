package com.example.demo.entity;
/**
 * 节点信息
 * 
 * @author LCC
 * @date 2014年8月19日
 */
public class ForerunnerNode {
	/**
	 * 主键字段
	 */
	private String fid;
	
	/**
	 * 下拉列表
	 */
	private ForerunnerSelect select;
	/**
	 * 树
	 */
	private ForerunnerTree tree;
	
	/// Getter And Setter ///
	public ForerunnerSelect getSelect() {
		return select;
	}
	public void setSelect(ForerunnerSelect select) {
		this.select = select;
	}
	public ForerunnerTree getTree() {
		return tree;
	}
	public void setTree(ForerunnerTree tree) {
		this.tree = tree;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
}
