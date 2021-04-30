import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class JwtUtils {

    static String token;

    public static void main(String[] args) throws InterruptedException {
        generateJwt();
        TimeUnit.SECONDS.sleep(10);
        testParseJwt();
    }

    public static void generateJwt() {

        Map<String, Object> claim = new HashMap<>();
        claim.put("name", "wmk");
        claim.put("age", "100");
        claim.put("school", "sc");

        JwtBuilder builder = Jwts.builder()
                .setId("110")             //设置唯一编号
                .setSubject("牛逼")       //设置主题  可以是JSON数据
                .setIssuedAt(new Date())  //设置签发日期
                .setClaims(claim)           //注意先后顺序
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 10))  //注意先后顺序
                .signWith(SignatureAlgorithm.HS256, "wqeqwewqeqwewq");//设置签名 使用HS256算法，并设置SecretKey(字符串)

        //构建 并返回一个字符串
        token = builder.compact();
        System.out.println(token);
    }

    public static void testParseJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("wqeqwewqeqwewq")
                .parseClaimsJws(token)
                .getBody();

        System.out.println(claims);
    }

}
