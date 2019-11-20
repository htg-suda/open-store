package com.htg.admin.utils;

import com.htg.admin.constants.JWTConst;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    /* 3600*/
     private static final Long interval = 3600L * 1000 * 24 * 7;
   // private static final Long interval = 3000L;

    /*签发人*/
    private static final String ISS = "TG.H";

    public static String generateJWTByUserName(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);

        Date now = new Date();
        Date exp = new Date(now.getTime() + interval);

        JwtBuilder builder = Jwts.builder();
        builder.setClaims(map);
        /* 设置 签发时间*/
        builder.setIssuedAt(now);
        /* 设置过期时间 */
        builder.setExpiration(exp);
        /* 设置签发人 */
        builder.setIssuer(ISS);


        /* 签名方式*/
        builder.signWith(SignatureAlgorithm.HS256, generalKey());
        /* 生成 token */
        String token = builder.compact();

        return token;
    }

    public static SecretKey generalKey() {
        String stringKey = JWTConst.SECRET;                                              //本地配置文件中加密的密文
        byte[] encodedKey = Base64.decodeBase64(stringKey);                              //本地的密码解码
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES"); // 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有.
        return key;
    }


    public static Claims parseJWT(String jwt) {
        SecretKey key = generalKey();           //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()           //得到DefaultJwtParser
                .setSigningKey(key)             //设置签名的秘钥
                .parseClaimsJws(jwt).getBody(); //设置需要解析的jwt
        return claims;
    }

    public static String getUserName(String jwt)  {
        Claims claims = parseJWT(jwt);
        return claims.get("username",String.class);
    }

}
