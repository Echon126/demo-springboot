package com.example.demo.utils.constants;
/**
 * 系统中的常量
 * @author LCC
 * @Date 2014年8月7日
 */
public interface Constants {
	/**
	 * 获取当前用户的KEY<br>
	 * 适用于Session/Model
	 */
	public final static String CURRENT_USER= "currentUser";
	/**
	 * Cache Key<br>
	 * 适用于框架自身的缓存
	 */
	public final static String CACHE_KEY= "CacheKey";
	/**
	 * Json 参数对应的Key<br>
	 * 适用于Model
	 */
	public final static String JSON_KEY= "json";
	/**
	 * 分页的起始值 Key<br>
	 * 适用于Model
	 */
	public final static String START_KEY= "start";
	/**
	 * 分页的条数 Key<br>
	 * 适用于Model
	 */
	public final static String LIMIT_KEY= "limit";
	/**
	 * 当没有配置文件的时候，通过在数组的最后一个元素追加该元素来确定这个变量是数组<br>
	 * 适用于拦截器参数注入时
	 */
	public final static String IS_ARRAY_KEY= "_is_array_";
}
