package com.wmk.utils;

import com.alibaba.fastjson.JSON;
import org.apache.catalina.webresources.FileResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/30
 * @TIME: 19:14
 **/
public class JWT {
    public static void main(String[] args) {
//        createToken();
        parseToken();

    }

    public static void createToken(){
        //证书文件路径
        String key_location="key.jks";
        //秘钥库密码
        String key_password="storepass";
        //秘钥密码
        String keypwd = "keypass";
        //秘钥别名
        String alias = "token";

        //访问证书路径
//        ClassPathResource resource = new ClassPathResource(key_location);
        FileSystemResource resource = new FileSystemResource("C:\\Users\\wmk\\IdeaProjects\\springcloud\\spring-cloud-oauth2\\oauth-server\\src\\main\\java\\com\\wmk\\utils\\key.jks");

        //创建秘钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource,key_password.toCharArray());

        //读取秘钥对(公钥、私钥)
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias,keypwd.toCharArray());

        //获取私钥
        RSAPrivateKey rsaPrivate = (RSAPrivateKey) keyPair.getPrivate();

        //获取公钥
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

        //定义Payload
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("id", "1");
        tokenMap.put("name", "wmk");
        tokenMap.put("roles", "ROLE_VIP,ROLE_USER");

        //生成Jwt令牌
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(tokenMap), new RsaSigner(rsaPrivate));

        //取出令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }

    public static void parseToken(){
        //令牌
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6IlJPTEVfVklQLFJPTEVfVVNFUiIsIm5hbWUiOiJ3bWsiLCJpZCI6IjEifQ.LdxnH2ZRRegmRCK0PhhS9ZSph5dPZ6MOF8a0v5u76bhgLfOr8uc4cmq1Ydv6kR0c4zkAcqwSWLpNo8OHWNj6aOWSzEzgT98fMt70z2Pmp213AT2X3i4wKFYR5kiExMu-cAPoTqvAcXJPlwjobdSkSWuKMz5ih6aFxbmeCoW2bK5KAhlGbn9Xs6ENGZ9cCez6AGNvTW2lZpZLzWAqSBjhaqmLoxUhBghu_aZB6gRafDQDghajLaZth5CKYzjVlzQt9jTHW95egTS0ES3qN1OpyW5qZAY7VQ9YUH7Moj3QjmMorMv-6J9LJ6pZQ0DmZvZWMbbBZ283WWopFEKo-xqbfg";

        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgs+DYPQbwkCApblmXaxlSmjIAIaGfI/a/2OIm8jP2a0I9XIsi0dxWJqrtoDpGNEbbJ2QCwaKBWAuqieEfGIdRcY7nSm6rvC6AOGrd1qMMcKx/nQGnWkX9CqhZ0LXyRhKxZUD36ltuza98ekYg8RYAG7bM47BCfWwZxz/LaF5KYJy2LXCwg4ZfMipAmBQ06eXBdcH8UqgrQBcXKfjuavszBz4RbCpbxka9MfX0Tp4BGhnebpplUFAPPIQ8ESbfNvOb23Yl0MMCCHLWGwnTjeivi6e7ynI+MWvqIH7YhwKdmyHyVi12QRQHRWq/RPtirwg6lBESbL11ztT+jaIVzQQ4wIDAQAB-----END PUBLIC KEY-----";

        //校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }
}
