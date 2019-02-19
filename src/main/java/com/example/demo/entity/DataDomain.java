package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 数据权限
 * 
 * @author LCC
 * @date 2014年11月7日
 */
public class DataDomain implements Serializable {
	private static final long serialVersionUID = -8663944471898702542L;
	/**
	 * 自身所所拥有的权限
	 */
	private Map<String, Set<String>> domain = new HashMap<>();
	/**
	 * 系统所拥有的数据权限类型
	 */
	private Set<String> dtypes = new HashSet<>();
	
	/// Methods ///
	/**
	 * 只是用来检查有没有权限的设置.
	 * 
	 * @author LCC
	 * @date 2014年11月7日
	 * @return
	 */
	public boolean hasDomain(){
		return domain != null && domain.size() > 0;
	}
	/**
	 * 获取所有的键值
	 * 
	 * @author LCC
	 * @date 2014年11月7日
	 * @return
	 */
	public Set<String> keySet(){
		return domain.keySet();
	}
	/**
	 * 获取数据权限
	 * 
	 * @author LCC
	 * @date 2014年11月7日
	 * @param key
	 * @return
	 */
	public Set<String> get(String key){
		return domain.get(key);
	}
	/**
	 * 如果不存在返回一个空
	 * 
	 * @author LCC
	 * @date 2014年12月11日
	 * @param key
	 * @return
	 */
	public Set<String> find(String key){
		return get(key) == null ? new HashSet<String>() : get(key);
	}
	/**
	 * 获取数据权限-转换成数组
	 * 
	 * @author LCC
	 * @date 2014年11月7日
	 * @param key
	 * @return
	 */
	public String[] getArray(String key){
		Set<String> s = domain.get(key);
		return s == null ? null : s.toArray(new String[s.size()]);
	}
	/**
	 * 会自动把值压入到List中
	 * 
	 * @author LCC
	 * @date 2014年11月7日
	 * @param key
	 * @param domain
	 */
	public void put(String key, String dv){
		if(domain.containsKey(key)){
			domain.get(key).add(dv);
		}else{
			Set<String> s = new HashSet<>();
			s.add(dv);
			domain.put(key, s);
		}
	}
	/**
	 * 直接放入一个完整的数据权限
	 * 
	 * @author LCC
	 * @date 2014年11月7日
	 * @param key
	 * @param domain
	 */
	public void put(String key, Set<String> dvs){
		domain.put(key, dvs);
	}
	/**
	 * 添加一个系统中所拥有的数据权限类型
	 * 
	 * @author LCC
	 * @date 2014年11月28日
	 * @param dtype
	 */
	public void addDtype(String dtype){
		dtypes.add(dtype);
	}
	
	/// Getter And Setter ///
	public Map<String, Set<String>> getDomain() {
		return domain;
	}
	public void setDomain(Map<String, Set<String>> domain) {
		this.domain = domain;
	}
	public Set<String> getDtypes() {
		return dtypes;
	}
	public void setDtypes(Set<String> dtypes) {
		this.dtypes = dtypes;
	}
}
