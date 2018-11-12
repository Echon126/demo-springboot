package com.example.demo.data;

import com.example.demo.entity.Content;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @date 2018-9-20 10:05
 */
public class ContentData {
    private Map<String, Content> map = Maps.newHashMap();
    private List<Object> list;
    //连接器
    private static final Joiner joiner = Joiner.on(",").skipNulls();
    //分割器
    private static final Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();

    public ContentData() {
        initMap();

        list = Lists.asList(new Content("code", "ddddd"), new Content[]{new Content("ddd", "ddd"), new Content("dddd", "dddd")});
    }

    public void initMap() {
        map.put("3", new Content("0001", "xigua"));
        map.put("2", new Content("0002", "donggua"));
        map.put("1", new Content("0003", "nanggua"));

        for (int i = 0; i < 1000000; i++) {
            map.put("0" + i, new Content("0003" + i, "nangua" + i));
        }
    }


    //entrySet 迭代方式
    public void SoutMap001() {
        System.out.println("========entrySet==========");
        long startTime = System.currentTimeMillis();
        for (Iterator<Map.Entry<String, Content>> it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Content> entry = it.next();
            //System.out.println(entry.getKey() + "----" + entry.getValue());
        }
        System.out.println("entrySet执行耗时ms " + (System.currentTimeMillis() - startTime));
    }


    //  keyset 迭代方式
    public void SoutMap002() {
        long startTime = System.currentTimeMillis();
        for (String key : map.keySet()) {
            Content c = map.get(key);
        }
        System.out.println("keyset执行耗时ms" + (System.currentTimeMillis() - startTime));
    }

    //value 方式执行
    public void SoutMap003() {
        long startTime = System.currentTimeMillis();

        for (Content c : map.values()) {
            Content content = c;
        }
        System.out.println("values执行耗时ms" + (System.currentTimeMillis() - startTime));
    }

    public final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static String getAddressIp() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static void main(String[] args) {
        String join =joiner.join(Lists.newArrayList("a","","b"));
        System.out.println("join="+join);

        for(String temp :splitter.split(" a,   ,b,,")){
            System.out.println("|"+temp+"|");
        }
    }
}
