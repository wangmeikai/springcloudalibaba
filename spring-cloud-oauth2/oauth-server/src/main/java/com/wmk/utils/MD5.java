package com.wmk.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/5/1
 * @TIME: 14:53
 **/
public class MD5 {

    public static void main(String[] args) {
        String str = "abcd";
        String salt = "123";
        String encrypt1 = encrypt(str,salt);
        String encrypt2 = encrypt(str,salt+1);
        System.out.println(encrypt1.equals(encrypt2));
    }

    public static String encrypt(String dataStr,String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(dataStr.getBytes(StandardCharsets.UTF_8));
            messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
            byte s[] = messageDigest.digest();
            return new String(s);
        } catch (Exception e) {
            e.printStackTrace();
            return "加密出错！";
        }
    }
}
