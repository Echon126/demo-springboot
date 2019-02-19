package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 将Forerunner返回的数据格式进行处理为树状结构
 * 
 * @author LCC
 * @date 2014年8月13日
 */
public class DefaultTreeConverter extends ConverterFactory{
	private ForerunnerTree st;
	/**
	 * 起始的父节点ID
	 */
	private String startPid = null;
	private Boolean multiSelect = null;
	
	public DefaultTreeConverter() {}
	public DefaultTreeConverter(String startPid) {
		this.startPid = startPid;
	}

	@Override
	public List<DataMap> toConverter(List<DataMap> dl) {
		// 获得树节点对应的字段
		st = node.getTree();
		// 如果不存在配置, 就只能返回原有的数据
		if(st == null){return dl;}
		
		return converter(dl, startPid);
	}
	
	protected List<DataMap> converter(List<DataMap> dl, String currPid){
		List<DataMap> ndl = new ArrayList<>();
		
		// 第一步先把当前父节点符合的信息筛选出来并从当前的集合中移除
		Iterator<DataMap> dmi = dl.iterator();
		while(dmi.hasNext()) {
			DataMap dm = dmi.next();
			
			Object opid = dm.get(st.getPid().getFiled());
			// 如果父节点信息不符合, 就继续循环
			if(!eq(opid, currPid)){continue; }
			
			String oid = dm.getValue(st.getId().getFiled()).toString();
			String otext = dm.getValue(st.getText().getFiled()).toString();
			
			Object oleaf = null;
			if(st.getLeaf().getFiled() != null){
				oleaf = dm.get(st.getLeaf().getFiled());
			}
			
			// 清除多余字段
			dm.clear(isClearUnnecessary());
			
			dm.put(st.getId().getUi(), oid);
			
			if(this.getJustName()){
				dm.put(st.getText().getUi(), oid);
			}else{
				dm.put(st.getText().getUi(), oid+"-"+otext);
			}
			
			dm.put(st.getPid().getUi(), opid);
			
			if(st.getLeaf().getFiled() != null && !dm.containsKey("leaf")){
				dm.put(st.getLeaf().getUi(), oleaf);
			}
			
			/**
			 * 如果是多选的话就加上checked这个属性
			 */
			if(multiSelect){
				dm.put("checked", false);
			}
			
			ndl.add(dm);
		}
		// 第二步 清除已经找到的元素
		for (DataMap dm : ndl) {
			dl.remove(dm);
		}
		// 第三步, 检查是否拥有子节点.
		for (DataMap dm : ndl) {
			Object oid = dm.get(st.getId().getUi());
			
			List<DataMap> cs = converter(dl, oid.toString());
			
			boolean isleaf = cs.size() == 0;
			if(!isleaf){
				dm.put(st.getChildren().getUi(), cs);
			}
			if(st.getLeaf().getFiled() == null && !dm.containsKey("leaf")){
				dm.put(st.getLeaf().getUi(), isleaf);
			}
		}
		
		return ndl;
	}
	
	/**
	 * 比较父节点信息是否符合
	 * 
	 * @author LCC
	 * @date 2014年8月19日
	 * @return
	 */
	private boolean eq(Object pid, String currPid){
		if(currPid == null || pid == null){
			return pid == currPid;
		}else{
			return currPid.equals(pid.toString());
		}
	}
	
	/// Getter And Setter ///
	public String getPid() {
		return startPid;
	}
	public void setPid(String pid) {
		this.startPid = pid;
	}
	public Boolean getMultiSelect() {
		return multiSelect;
	}
	public void setMultiSelect(Boolean multiSelect) {
		this.multiSelect = multiSelect;
	}
}
