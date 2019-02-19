package com.example.demo.entity;

import com.example.demo.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.ConverterFactory;

import java.io.Serializable;

/**
 * 基类-用来与DataMap做兼容性使用
 *
 * @author LCC
 * @date 2014年9月1日
 */
public class DataModel implements Serializable {
    private static final long serialVersionUID = 8040990734647409075L;
    Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 分页-起始数
     */
    private Integer start = 0;
    /**
     * 分页-显示数
     */
    private Integer limit = 15;
    /**
     * 文件名, 缓存Key, sqlxml的命名空间<br>
     */
    private String namespace;
    /**
     * 当前登录用户
     */
    private DataMap currentUser;
    /**
     * 数据转换接口
     */
    private ConverterFactory converter;
    /**
     * 配置文件的配置
     */
    private ForerunnerConfig config;

    /// Methods ///

    /**
     * 将对象转换为一个DataMap对象
     *
     * @return
     * @author LCC
     * @date 2014年9月2日
     */
    public DataMap toDataMap() {
        try {
            return ConvertClass.toDataMap(this);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("转换异常: {}; Exception: {}",
                    JsonUtils.toString(this), e.getMessage()
            );
        }
        return null;
    }

    /// Getter And Setter ///
    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public ConverterFactory getConverter() {
        return converter;
    }

    public void setConverter(ConverterFactory converter) {
        this.converter = converter;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public ForerunnerConfig getConfig() {
        return config;
    }

    public void setConfig(ForerunnerConfig config) {
        this.config = config;
    }

    public DataMap getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(DataMap currentUser) {
        this.currentUser = currentUser;
    }
}
