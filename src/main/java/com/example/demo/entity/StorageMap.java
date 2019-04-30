package com.example.demo.entity;



import com.example.demo.utils.ConvertTypeUtil;
import com.example.demo.utils.RegexpUtil;
import com.example.demo.utils.ValueUtil;
import com.example.demo.utils.constants.Constants;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 强化HashMap
 * 
 * @author LCC
 * @date 2014年8月22日
 */
public class StorageMap extends HashMap<String, Object> {
	private static final long serialVersionUID = 9031314752692473991L;
	/// Attribute ///
	/**
	 * 对字段的配置
	 */
	protected ForerunnerConfig config;/**
	
	 * 当前登录用户
	 */
	protected DataMap user;
	/**
	 * 当前访问中, 受限制的字段访问的处理结果.
	 */
	protected Map<String, Boolean> currDomain = new HashMap<>();
	
	/// Methods ///
	/**
	 * 该值在进入Map之前会进行类型转换-主要用于拦截器的时候<br>
	 * 该方法使用于字符串传值. 因为在大多数时候你已经传入了一个强类型的值<br>
	 * 
	 * @author LCC
	 * @date 2014年8月20日
	 * @param key
	 * @param value
	 */
	public void setValue(String key, Object[] value){
		if(config == null || config.getFields() == null){ configOrFiledToNull(key, value); return; }
		
		ForerunnerField f = config.getFields().get(key);
		
		if(f == null){ configOrFiledToNull(key, value); return; }
		
		String type = f.getType();
		
		try {
			if(type == null || type.indexOf("[]") == -1){
				Object v = RegexpUtil.filter(value[0].toString(), f.getFilter());
				if(!this.isDomainAdmin()){ v = domain(v, f); }
				this.put(key, ConvertTypeUtil.converter(type, v));
			}else{
				Object[] vs = RegexpUtil.filter(value, f.getFilter());
				if(!this.isDomainAdmin()){ vs = domain(vs, f); }
				this.put(key, ConvertTypeUtil.converToArray(type.replace("[]", ""), vs));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 检测数据权限是否存在
	 * 
	 * @author LCC
	 * @date 2014年11月6日
	 * @param v
	 * @param f
	 * @return
	 */
	private Object domain(Object v, ForerunnerField f){
		if(this.noLogin()){return v;}
		
		String domain = f.getDomain();
		// 如果规定了数据权限, 不拦截空字符串, 由后台SqlXml自己处理
		if(StringUtils.isNoneEmpty(v.toString()) && StringUtils.isNoneEmpty(domain)){
			// 如果用户不存在, 自然也就没有数据权限了
			if(user.get("dvalue") == null){ return null; }
			
			DataDomain dd = (DataDomain) user.get("dvalue");
			if(getValue(domain + "_Admin").toBoolean()){
				return v;
			}
			if(dd.hasDomain()){
				// 记录当前这个字段是否有权限
				Set<String> t = dd.find(domain);
				currDomain.put(domain, t.contains(v));
				return t.contains(v) ? v : null;
			}
			return null;
		}
		return v;
	}
	private Object[] domain(Object[] vs, ForerunnerField f){
		if(this.noLogin()){return vs;}
		
		Set<Object> ds = new HashSet<>();
		// 如果规定了数据权限
		String domain = f.getDomain();
		if(StringUtils.isNoneEmpty(domain)){
			// 如果用户不存在, 自然也就没有数据权限了
			if(user.get("dvalue") == null){ return null; }
			
			DataDomain dd = (DataDomain) user.get("dvalue");
			
			if(dd.hasDomain()){
				Set<String> dvalue = dd.find(domain);
				for (Object v : vs) {
					if(getValue(domain + "_Admin").toBoolean() || dvalue.contains(v)){ ds.add(v); }
				}
			}
			// 记录当前这个字段是否有权限
			currDomain.put(domain, ds.size() > 0);
			return ds.toArray();
		}
		return vs;
	}
	public boolean noLogin(){
		return this.getUser() == null;
	}
	/**
	 * 获得一个值
	 * 
	 * @author LCC
	 * @date 2014年8月20日
	 * @param key
	 * @return
	 */
	public ConvertMapValue getValue(String key){
		return new ConvertMapValue(this.get(key));
	}
	/**
	 * 当配置和字段的配置不存在的时候
	 * @param
	 */
	public void configOrFiledToNull(String key, Object[] os){
		int maxIndex = os.length-1;
		String isArray = os[maxIndex].toString();
		// 判断数组最后一个元素是否带有 “我是数组” 的标志
		if(Constants.IS_ARRAY_KEY.equals(isArray)){
			Object[] nos = new Object[maxIndex];
			for (int i = 0; i<maxIndex; i++) {
				nos[i] = ValueUtil.trimValue(os[i]);
			}
			
			this.put(key, nos); return;
		}
		this.put(key, os[0]); return;
	}
	/**
	 * 是否拥有主键
	 * 
	 * @author LCC
	 * @date 2014年10月30日
	 * @return
	 */
	public boolean hasId(){
		String fid = this.getConfig().getNode().getFid();
		if(this.containsKey(fid) && StringUtils.isNotEmpty(this.getValue(fid).toString())){
			return true;
		}
		return false;
	}
	/**
	 * 存储用户信息
	 */
	public void setUser(DataMap user) {
		// 用于在sql中判断是否拥有admin的数据权限
		if(user != null){
			this.put("isDomainAdmin", user.getValue("domainAdmin").toBoolean());
			this.put("isSuperAdmin", user.getValue("superAdmin").toBoolean());
		}
		this.user = user;
	}
	/**
	 * 是否无数据权限限制
	 * 
	 * @author LCC
	 * @date 2014年11月7日
	 * @return
	 */
	public boolean isDomainAdmin(){
		return this.getValue("isDomainAdmin").toBoolean();
	}
	public boolean isNotDomainAdmin(){
		return !isDomainAdmin();
	}
	public boolean isSuperAdmin(){
		return this.getValue("isSuperAdmin").toBoolean();
	}
	/**
	 * 不为超级管理员, 数据权限管理员, 以及当下权限管理员的时候. 判断当前的权限是否通过
	 * 
	 * @author LCC
	 * @date 2014年11月7日
	 * @return
	 */
	public boolean checkCurrDomain(){
		if(this.isDomainAdmin()){return true;}
		
		Map<String, Boolean> cd = this.getCurrDomain();
		Set<String> ks = cd.keySet();
		for (String k : ks) {
			// 是否是当下数据权限的管理员, 如果没有, 检查是否数据权限存在于当前的权限集合中.
			if(!this.getValue(k + "_Admin").toBoolean() && !cd.get(k)){
				return false;
			}
		}
		return true;
	}
	
	/// Getter And Setter ///
	public ForerunnerConfig getConfig() {
		return config;
	}
	public void setConfig(ForerunnerConfig config) {
		this.config = config;
	}
	public DataMap getUser() {
		return user;
	}
	public Map<String, Boolean> getCurrDomain() {
		return currDomain;
	}
	public void setCurrDomain(Map<String, Boolean> currDomain) {
		this.currDomain = currDomain;
	}
}
