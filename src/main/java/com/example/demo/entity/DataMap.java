package com.example.demo.entity;

import com.example.demo.utils.JsonUtils;
import com.example.demo.utils.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.List;
import java.util.Set;

/**
 * 数据载体
 * @author LCC
 * @Date 2014年8月7日
 */
public class DataMap extends StorageMap {
	private static final long serialVersionUID = -7931652494373740940L;
	Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * 数据转换接口
	 */
	private ConverterFactory converter;
	
	/**
	 * 文件名, 缓存Key<br>
	 * 冗余扩展属性
	 */
	private String name;
	/**
	 * 指定MyBatis的SqlId<br>
	 * 目的是增大底层代码的利用率
	 */
	private String sqlId;
	
	/// Methods ///
	/**
	 * 获得MyBatis的Sql ID<br>
	 * 规则是this.name + 被调用处的方法名.<br>
	 * 例如: name为student. 调用该方法的名字为insert. 那么结果: student.insert
	 * @return
	 */
	public String getMyBatisSqlId(){
		if(StringUtils.isNotEmpty(sqlId)){
			return sqlId;
		}
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		String temp = this.getName() + "." + ste.getMethodName();
		return temp;
	}
	/**
	 * 将前台传来的Json String解析为一组DataMap List.
	 * @return DataMap List
	 */
	public List<DataMap> getJsonStrToDataMaps(){
		List<DataMap> dl = null;
		dl = JsonUtils.parseArray(this.get(Constants.JSON_KEY).toString(), DataMap.class);
		for(DataMap dm : dl) {
			dm.user = user;
			dm.config = config;
			dm.sqlId = sqlId;
		}
		return dl;
	}
	
	/**
	 * 将前台传来的Json String解析为一组DataMap List.
	 * 自定义dataName参数
	 * @return DataMap List
	 */
	public List<DataMap> getJsonStrToDataMaps(String DataName){
		List<DataMap> dl = null;
		dl = JsonUtils.parseArray(this.get(DataName).toString(), DataMap.class);
		this.remove(DataName);
		for(DataMap dm : dl) {
			dm.user = user;
			dm.config = config;
			dm.sqlId = sqlId;
			dm.putAll(this);
		}
		return dl;
	}
	/**
	 * 清空对象
	 * 
	 * @author LCC
	 * @date 2014年8月19日
	 * @param isClear
	 */
	public void clear(boolean isClear){
		if(isClear){
			this.clear();
		}
	}
	/**
	 * 删除一组值
	 * 
	 * @author LCC
	 * @date 2014年10月28日
	 * @param keys
	 */
	public void removes(Set<Object> keys){
		for (Object key : keys) {
			this.remove(key);
		}
	}
	/**
	 * 将对象转换成对应的Model
	 * 
	 * @author LCC
	 * @date 2014年9月2日
	 * @param type
	 * @return
	 */
	public <T extends DataModel> T toModel(Class<T> type){
		try {
			return ConvertClass.toModel(this, type);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("转换异常: {}; Exception: {}", 
					JsonUtils.toString(this), e.getMessage()
			);
		}
		return null;
	}
	/**
	 * 快速插入键值<br>
	 * 例如: "key:value,key:value..."<br>
	 * 不适合value为复杂内容的插入
	 * 
	 * @author LCC
	 * @date 2014年10月17日
	 * @param expression
	 */
	public void easyInsert(String expression){
		if(StringUtils.isEmpty(expression)){return;}
		String[] t1 = expression.split(",");
		for (String s : t1) {
			String[] t2 = s.split(":");
			if(t2.length != 2){continue;}
			this.put(t2[0], t2[1]);
		}
	}
	/**
	 * 快速插入键值<br>
	 * 例如: simpleInsert("key","String","key",number...)<br>
	 * 切记一定要是偶数
	 * 
	 * @author LCC
	 * @date 2014年10月17日
	 * @param ps
	 */
	public void simpleInsert(Object...ps){
		int max = ps.length;
		max = max%2==0 ? max : max-1;
		for (int i=0; i<max; i+=2) {
			this.put(ps[i].toString(), ps[i+1]);
		}
	}
	/**
	 * 快速创建一个带有内容的
	 * 
	 * @author LCC
	 * @date 2014年10月17日
	 * @param expression
	 * @return
	 */
	public static DataMap easyCreate(Object...ps){
		DataMap dm = new DataMap();
		if(ps != null && ps.length > 1){
			dm.simpleInsert(ps);
		}else{
			dm.easyInsert(ps[0].toString());
		}
		return dm;
	}
	
	/// Getter And Setter ///
	public ConverterFactory getConverter() {
		return converter;
	}
	public void setConverter(ConverterFactory converter) {
		this.converter = converter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSqlId() {
		return sqlId;
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
}
