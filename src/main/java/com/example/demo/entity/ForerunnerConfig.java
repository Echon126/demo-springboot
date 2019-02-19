package com.example.demo.entity;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @date 2019-2-18 11:19
 */
public class ForerunnerConfig {
    /**
     * 文件名, 缓存Key<br>
     * 冗余扩展属性
     */
    private String name;
    /**
     * 配置文件对象<br>
     * 冗余扩展属性
     */
    private File cfile;
    /**
     * 表名
     */
    private String table;
    /**
     * 别名, 中文名称
     */
    private String alias;
    /**
     * 所有字段
     */
    private Map<String, ForerunnerField> fields;
    /**
     * 一组外键关联
     */
    private List<ForerunnerLink> links;
    /**
     * 节点信息
     */
    private ForerunnerNode node;

    /// Getter And Setter ///
    public String getTable() {
        return table;
    }
    public void setTable(String table) {
        this.table = table;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public Map<String, ForerunnerField> getFields() {
        return fields;
    }
    public void setFields(Map<String, ForerunnerField> fields) {
        this.fields = fields;
    }
    public List<ForerunnerLink> getLinks() {
        return links;
    }
    public void setLinks(List<ForerunnerLink> links) {
        this.links = links;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public File getCfile() {
        return cfile;
    }
    public void setCfile(File cfile) {
        this.cfile = cfile;
    }
    public ForerunnerNode getNode() {
        return node;
    }
    public void setNode(ForerunnerNode node) {
        this.node = node;
    }

}
