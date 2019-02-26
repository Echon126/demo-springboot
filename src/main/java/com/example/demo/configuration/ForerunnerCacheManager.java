package com.example.demo.configuration;

import com.example.demo.entity.*;
import com.example.demo.utils.JsonUtils;
import com.example.demo.utils.SimpleProperties;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author admin
 * @date 2019-2-18 11:11
 */
public class ForerunnerCacheManager {
    private final static Logger logger = LoggerFactory.getLogger(ForerunnerCacheManager.class);

    public static void init(String path) throws JDOMException, IOException {
        logger.debug("Forerunner配置文件的路径：{}", path);
        File xmls = new File(path);
        if(xmls.exists() && xmls.isDirectory()){
            File[] files = xmls.listFiles();
            SAXBuilder sb = new SAXBuilder();
            for (File f : files) {
                String[] tem = f.getName().split("\\.");
                String suffix = tem[tem.length-1];
                switch(suffix){
                    case "xml":
                        Document build = sb.build(f);
                        readConfig(f.getName().replace(".xml", ""), f, build.getRootElement());
                        break;
                    case "properties":
                        initExceptionInfo(f);
                        break;
                }
            }

            initFK();
        }
    }

    /**
     * 初始化外键关联-将对应的字段一一对应上<br>
     * 字段级别的对应放在取字段集合的时候对应
     *
     * @author LCC
     * @date 2014年9月19日
     */
    public static void initFK(){
        Set<String> keys = ForerunnerCache.getKeySet();
        for (String key : keys) {
            ForerunnerConfig config = ForerunnerCache.getConfig(key);
            List<ForerunnerLink> links = config.getLinks();

            Map<String, ForerunnerField> fields = config.getFields();
            if(fields == null){
                fields = new HashMap<>();
            }else{
                Set<String> ks = fields.keySet();
                for (String k : ks) {
                    ForerunnerField f = fields.get(k);
                    if(f.getLink() != null){
                        try {
                            f.getLink().setAlias();
                        } catch (Exception e) {
                            logger.error("Forerunner配置文件[{}]的[{}]字段配置不完整...", config.getName(), f.getName());
                        }
                    }
                }
            }

            // 如果没有外键的关联就进行下一个判断
            if(links == null){continue;}

            for (ForerunnerLink link : links) {
                ForerunnerConfig fkc = ForerunnerCache.getConfig(link.getValue());
                // 如果配置文件没有获取到就说明该配置文件不存在
                if(fkc == null){
                    logger.warn("没有找到[{}]的配置文件!", link.getValue());
                    continue;
                }
                Map<String, ForerunnerField> fkfs = fkc.getFields();
                // 说明这个文件只是一个空壳
                if(fkfs == null){
                    logger.warn("[{}]的配置不存在字段的配置!", fkc.getName());
                    continue;
                }
                // 开始进行字段的装载
                Set<String> fkKeys = fkfs.keySet();
                for (String fkKey : fkKeys) {
                    // 如果已经存在这个key了, 就不添加了, 防止被覆盖
                    if(fields.containsKey(fkKey)){continue;}
                    ForerunnerField o = fkfs.get(fkKey);
                    // 如果没有别名 或者是 这个字段来自于其他的配置, 就不进行装载了.
                    boolean isPut = StringUtils.isEmpty(o.getAlias()) || o.isFkField();
                    if(isPut){continue;}
                    // 进行浅复制, 否则会影响到原来字段的值.
                    ForerunnerField n = new ForerunnerField();
                    try {
                        BeanUtils.copyProperties(n, o);

                        n.setFkField(true);
                        fields.put(fkKey, n);
                    } catch (Exception e) {
                        logger.warn("配置复制时出错了..., 错误信息[{}]", e.getMessage());
                    }
                }
            }
            config.setFields(fields);
        }
    }


    public static void readConfig(String fname, File f, Element root) {
        if (root == null) return;

        ForerunnerConfig config = new ForerunnerConfig();
        config.setName(fname);
        config.setCfile(f);
        config.setTable(eleAttrValueTrim(root, "table"));
        config.setAlias(eleAttrValueTrim(root, "alias"));

        config.setFields(readFields(root.getChild("fields")));
        config.setLinks(readLinks(root.getChild("links")));
        config.setNode(readNode(root.getChild("node")));

        ForerunnerCache.addConfig(fname, config);

        logger.debug("配置文件{}内容:{}", fname, JsonUtils.toString(config));
    }

    // 读取字段配置信息
    private static Map<String, ForerunnerField> readFields(Element eleFields) {
        if (eleFields == null) {
            return null;
        }

        Map<String, ForerunnerField> fields = new HashMap<>();
        List<Element> childrens = eleFields.getChildren();
        for (Element children : childrens) {
            ForerunnerField field = new ForerunnerField();
            field.setAlias(eleAttrValueTrim(children, "alias"));
            field.setType(eleAttrValueTrim(children, "type"));
            field.setName(eleAttrValueTrim(children, "name"));
            field.setDomain(eleAttrValueTrim(children, "domain"));
            field.setFilter(eleAttrValueTrim(children, "filter"));
            field.setDictionary(eleAttrValueTrim(children, "dictionary"));
            String exSort = eleAttrValueTrim(children, "exSort");
            if (exSort != null) {
                field.setExSort(Integer.valueOf(exSort));
            }
            field.setExAlias(eleAttrValueTrim(children, "exAlias"));
            String imSort = eleAttrValueTrim(children, "imSort");
            if (imSort != null) {
                field.setImSort(Integer.valueOf(imSort));
            }
            field.setValidate(readValidate(children.getChild("validate")));
            field.setLink(readLink(children.getChild("link")));

            fields.put(field.getName(), field);
        }

        return fields;
    }

    // 读取验证信息
    private static ForerunnerValidate readValidate(Element eleValidate) {
        if (eleValidate == null) {
            return null;
        }

        ForerunnerValidate validate = new ForerunnerValidate();

        validate.setMin(eleText(eleValidate.getChild("min")));
        validate.setMax(eleText(eleValidate.getChild("max")));
        validate.setCustom(eleText(eleValidate.getChild("custom")));
        validate.setRequired(Boolean.valueOf(eleText(eleValidate.getChild("required"))));

        return validate;
    }

    // 读取外键关联配置
    private static ForerunnerLink readLink(Element eleLink) {
        if (eleLink == null) {
            return null;
        }

        ForerunnerLink link = new ForerunnerLink();

        link.setField(eleAttrValueTrim(eleLink, "field"));
        link.setValue(eleAttrValueTrim(eleLink, "value"));
        link.setUrl(eleAttrValueTrim(eleLink, "url"));

        return link;
    }

    // 读取一组配置文件集合
    private static List<ForerunnerLink> readLinks(Element eleLinks) {
        if (eleLinks == null) {
            return null;
        }

        List<ForerunnerLink> links = new ArrayList<>();
        for (Element children : eleLinks.getChildren()) {
            links.add(readLink(children));
        }

        return links;
    }

    // 读取节点信息
    private static ForerunnerNode readNode(Element eleNode) {
        if (eleNode == null) {
            return null;
        }

        ForerunnerNode node = new ForerunnerNode();
        node.setFid(eleAttrValueTrim(eleNode, "fid"));

        // 读取下拉列表的节点
        ForerunnerSelect select = new ForerunnerSelect();
        Element eselect = eleNode.getChild("select");
        if (eselect != null) {
            select.setKey(eleAttrValueTrim(eselect, "key"));
            select.setValue(eleAttrValueTrim(eselect, "value"));

            node.setSelect(select);
        }

        // 读取树的节点
        ForerunnerTree tree = new ForerunnerTree();
        Element etree = eleNode.getChild("tree");
        if (etree != null) {
            tree.setId(readTreeNode(etree.getChild("id")));
            tree.setText(readTreeNode(etree.getChild("text")));
            tree.setPid(readTreeNode(etree.getChild("pid")));
            tree.setLeaf(readTreeNode(etree.getChild("leaf")));
            tree.setChildren(readTreeNode(etree.getChild("children")));

            node.setTree(tree);
        }

        return node;
    }

    // 获得树节点信息
    private static ForerunnerTree.TreeNode readTreeNode(Element e) {
        if (e == null) {
            return null;
        }
        ForerunnerTree.TreeNode node = new ForerunnerTree().new TreeNode();
        node.setFiled(eleAttrValueTrim(e, "filed"));
        node.setUi(eleAttrValueTrim(e, "ui", e.getName()));

        return node;
    }

    //------------------------------------------------工具类------------------------------------------------------------
    // 获取标签的值
    private static String eleText(Element e) {
        return e == null ? null : e.getTextTrim();
    }

    // 把配置文件的属性值取出空格
    private static String eleAttrValueTrim(Element e, String attr, String dvalue) {
        String v = eleAttrValueTrim(e, attr);
        return v == null ? dvalue : v;
    }

    private static String eleAttrValueTrim(Element e, String attr) {
        if (e == null) {
            return null;
        }
        String value = e.getAttributeValue(attr);
        if (StringUtils.isEmpty(value) || value.trim().length() == 0) {
            return null;
        }
        return value.trim();
    }

    /// ------- 异常信息配置 --------------- ///
    /**
     * 读取异常信息的配置文件
     *
     * @author LCC
     * @date 2014年8月27日
     * @param f
     */
    public static void initExceptionInfo(File f){
        try {
            SimpleProperties esp = ForerunnerCache.initExceptionInfo(f);
            logger.debug("Forerunner 异常信息配置文件读取成功, 配置内容: {}", JsonUtils.toString(esp));
        } catch (IOException e) {
            logger.warn("配置文件{}读取出错:{}", f.getName(), e.getMessage());
        }
    }
}
