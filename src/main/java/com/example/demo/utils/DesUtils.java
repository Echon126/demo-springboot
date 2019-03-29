package com.example.demo.utils;


import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author admin
 * @date 2019-3-18 16:41
 */
public class DesUtils {
    private final static String password = "zhangwenjie";


    //jdk 的实现
    public static void jdkDES() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        //1 生成key
        String algorithm = "DES";
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(56);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] bytesKey = secretKey.getEncoded();


        //2 key 转换
        DESKeySpec deSedeKeySpec = new DESKeySpec(bytesKey);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
        Key convertSecretKey = factory.generateSecret(deSedeKeySpec);
        System.out.println("key " + convertSecretKey.getAlgorithm());

        //3 加密
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
        byte[] result = cipher.doFinal(password.getBytes());
        System.out.println("机密结果 " + Hex.encodeHexString(result));


        //4.解密
        cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
        result = cipher.doFinal(result);
        System.out.println("解密结果：" + new String(result));

    }

    public static void main(String[] args) throws  NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
         jdkDES();
    }

}


















