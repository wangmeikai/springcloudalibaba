package com.wmk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        tokenMap.put("iat", System.currentTimeMillis()/1000);  //签证时间
        tokenMap.put("exp", System.currentTimeMillis()/1000 + 30);  //过期时间

        //生成Jwt令牌
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(tokenMap), new RsaSigner(rsaPrivate));

        //取出令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }

    public static void parseToken(){
        //令牌
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoid21rIiwiaWQiOiIxIiwiZXhwIjoxNjE5Nzg2MDMzLCJpYXQiOjE2MTk3ODYwMDN9.PtSvY57LgYBbQbPcxZ_nplv2rJlAEgb4WjR3Mq8cmoDNSgAoYmqd6uXy3pkgAwxaiAWLv2ucwsrVfChjoARNim0w5tctA5QSF4hZSF_u1BiAQHbdEydnna9wEAARqBBCfPyEL5-hItlnp3bAoW-8wrB-M_nLfVZ3Q4K2u2G1dGM36M0l_Fibprx0RcKusi4t28AxyYVXt2fRnzvtmZsYtu8TOIoRRLIRC8KnGopAF-3uqScQ4houcdH0V8XnNniIS1QRZF0EKWxNZG_VLmFTIKoH264LA2c_-MsYs23IZvOBQP10eUnLY8nZMgJzV_fBB2uWMQMY_kWNIhVL6BMp7A";

        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgs+DYPQbwkCApblmXaxlSmjIAIaGfI/a/2OIm8jP2a0I9XIsi0dxWJqrtoDpGNEbbJ2QCwaKBWAuqieEfGIdRcY7nSm6rvC6AOGrd1qMMcKx/nQGnWkX9CqhZ0LXyRhKxZUD36ltuza98ekYg8RYAG7bM47BCfWwZxz/LaF5KYJy2LXCwg4ZfMipAmBQ06eXBdcH8UqgrQBcXKfjuavszBz4RbCpbxka9MfX0Tp4BGhnebpplUFAPPIQ8ESbfNvOb23Yl0MMCCHLWGwnTjeivi6e7ynI+MWvqIH7YhwKdmyHyVi12QRQHRWq/RPtirwg6lBESbL11ztT+jaIVzQQ4wIDAQAB-----END PUBLIC KEY-----";

        //校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容
        String claims = jwt.getClaims();

        JSONObject jsonObject = JSON.parseObject(claims);
        long exp = Long.parseLong(jsonObject.get("exp").toString());
        System.out.println(exp);   //截止时间
        System.out.println(System.currentTimeMillis()/1000);  //当前时间
        if (exp < System.currentTimeMillis()/1000){
            throw new RuntimeException("jwt 已过期");
        }

        System.out.println(claims);

        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }
}
