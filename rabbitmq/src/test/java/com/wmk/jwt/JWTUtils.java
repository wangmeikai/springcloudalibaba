package com.wmk.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    public void testCreateJwt(){
        JwtBuilder builder= Jwts.builder()
                .setId("888")             //设置唯一编号
                .setSubject("小白")       //设置主题  可以是JSON数据
                .setIssuedAt(new Date())  //设置签发日期
                .setExpiration(new Date(System.currentTimeMillis()+60*1000))
                .signWith(SignatureAlgorithm.HS256,"itcast");//设置签名 使用HS256算法，并设置SecretKey(字符串)
        Map<String,Object> map = new HashMap<>();
        map.put("name","wmk");
        map.put("age","25");

        //添加自定义Claims
        builder.addClaims(map);
        //构建 并返回一个字符串
        System.out.println( builder.compact());
    }

    public void testParseJwt(){
        String compactJwt="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE2MDA0OTM3MzUsImV4cCI6MTYwMDQ5Mzc5NSwibmFtZSI6IndtayIsImFnZSI6IjI1In0.REESbtMTwimoDA71ISQuIK1OT_p3nIUPxC_PjJUts60";
        Claims claims = Jwts.parser().
                setSigningKey("itcast").
                parseClaimsJws(compactJwt).
                getBody();
        System.out.println(claims);
    }
}
