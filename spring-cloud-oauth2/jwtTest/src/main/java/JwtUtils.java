import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtils {

    static String token;

    public static void main(String[] args) {
        generateJwt();
        testParseJwt();
    }

    public static void generateJwt() {
        JwtBuilder builder= Jwts.builder()
                .setId("110")             //设置唯一编号
                .setSubject("牛逼")       //设置主题  可以是JSON数据
                .setIssuedAt(new Date())  //设置签发日期
                .signWith(SignatureAlgorithm.HS256,"abcd");//设置签名 使用HS256算法，并设置SecretKey(字符串)
        Map<String,Object> claim = new HashMap<>();
        claim.put("name","wmk");
        claim.put("age","100");
        claim.put("school","sc");
        builder.setClaims(claim);
        //构建 并返回一个字符串
        token = builder.compact();
        System.out.println(token);
    }

    public static void testParseJwt(){
        Claims claims = Jwts.parser().
                setSigningKey("abcd").
                parseClaimsJws(token).
                getBody();
        System.out.println(claims);
    }

}
