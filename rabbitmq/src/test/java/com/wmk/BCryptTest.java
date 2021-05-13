package com.wmk;

public class BCryptTest {
    public static void main(String[] args) {
        String pwd = "qwertyuiop";
        System.out.println("开始=================");
        String hashPwd1 = BCrypt.hashpw(pwd,BCrypt.gensalt());
        System.out.println("hashPwd1:"+hashPwd1);
        System.out.println("hashPwd1length:"+hashPwd1.length());
        System.out.println(BCrypt.checkpw(pwd,hashPwd1));

        String hashPwd2 = BCrypt.hashpw(pwd,BCrypt.gensalt(12));
        System.out.println("hashPwd2:"+hashPwd2);
        System.out.println("hashPwd2length:"+hashPwd2.length());
        System.out.println(BCrypt.checkpw(pwd,hashPwd2));
    }
}
