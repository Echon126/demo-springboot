package com.example.demo.utils;


import org.apache.commons.codec.binary.Base64;

/**
 * @author admin
 * @date 2019-2-28 10:46
 */
public class Base64Utils {

    /**
     * 解码，返回原数据的byte数组
     *
     * @param bytes
     * @return
     */
    public static byte[] decode(final byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }


    /**
     * 二进制数据编码为BASE64字符串
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(final byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

}
