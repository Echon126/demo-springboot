package com.example.demo.entity;
/**
 * 树节点对应的字段<br>
 * 
 * @author LCC
 * @Date 2014年8月9日
 */
public class ForerunnerTree {
	/**
	 * id
	 */
	private TreeNode id;;
	/**
	 * 节点名
	 */
	private TreeNode text;
	/**
	 * 父节点
	 */
	private TreeNode pid;
	/**
	 * 是否是叶子节点
	 */
	private TreeNode leaf;
	/**
	 * 孩子节点
	 */
	private TreeNode children;
	
	/**
	 * 节点
	 * 
	 * @author LCC
	 * @date 2014年8月19日
	 */
	public class TreeNode{
		/**
		 * 字段名
		 */
		private String filed;
		/**
		 * UI名
		 */
		private String ui;
		
		public TreeNode() { }
		public TreeNode(String ui) {
			this.ui = ui;
		}


		/// Getter And Setter ///
		public String getFiled() {
			return filed;
		}
		public void setFiled(String filed) {
			this.filed = filed;
		}
		public String getUi() {
			return ui;
		}
		public void setUi(String ui) {
			this.ui = ui;
		}
	}
	
	/// Getter And Setter ///
	public TreeNode getId() {
		return id;
	}
	public void setId(TreeNode id) {
		if(id==null){
			id = new TreeNode("id");
		}
		this.id = id;
	}
	public TreeNode getText() {
		return text;
	}
	public void setText(TreeNode text) {
		if(text==null){
			text = new TreeNode("text");
		}
		this.text = text;
	}
	public TreeNode getPid() {
		return pid;
	}
	public void setPid(TreeNode pid) {
		if(pid==null){
			pid = new TreeNode("pid");
		}
		this.pid = pid;
	}
	public TreeNode getLeaf() {
		return leaf;
	}
	public void setLeaf(TreeNode leaf) {
		if(leaf==null){
			leaf = new TreeNode("leaf");
		}
		this.leaf = leaf;
	}
	public TreeNode getChildren() {
		return children;
	}
	public void setChildren(TreeNode children) {
		if(children==null){
			children = new TreeNode("children");
		}
		this.children = children;
	}
}
