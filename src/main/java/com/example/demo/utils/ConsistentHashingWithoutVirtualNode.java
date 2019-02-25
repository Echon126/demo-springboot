package com.example.demo.utils;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author admin
 * @date 2019-2-21 11:24
 */
public class ConsistentHashingWithoutVirtualNode {

    /**
     * 待添加到Hash换的服务器列表
     */
    private static String[] severs = {"198.168.0.1:8080", "198.168.0.2:8080",
            "198.168.0.3:8080", "198.168.0.4:8080", "198.168.0.5:8080",};

    //key表示服务器的hash值，value表示服务器
    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

    static {
        for (int i = 0; i < severs.length; i++) {
            int hash = getHash(severs[i]);
            System.out.println("[" + severs[i] + "]假如集合中，其中Hash值为" + hash);
            sortedMap.put(hash, severs[i]);
        }
    }

    //使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;

        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.length()) * p;
            hash += hash << 13;
            hash ^= hash >> 7;
            hash += hash << 3;
            hash ^= hash >> 17;
            hash += hash << 5;
        }

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) hash = Math.abs(hash);

        return hash;
    }


    //得到应当路由到的结点
    public static String getServer(String key) {
        //得到该key的hash值
        int hash = getHash(key);
        //得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        if (subMap.isEmpty()) {
            //如果没有比该key的hash值大的，则从第一个node开始
            Integer i = sortedMap.firstKey();
            //返回对应的服务器
            return sortedMap.get(i);
        } else {
            //第一个Key就是顺时针过去离node最近的那个结点
            Integer i = subMap.firstKey();
            //返回对应的服务器
            return subMap.get(i);

        }
    }


    public static void main(String[] args) {
        String[] keys = {"太阳222", "月亮ss", "星星222000"};
        for(int i=0; i<keys.length; i++)
            System.out.println("[" + keys[i] + "]的hash值为" + getHash(keys[i])
                    + ", 被路由到结点[" + getServer(keys[i]) + "]");
    }


}































