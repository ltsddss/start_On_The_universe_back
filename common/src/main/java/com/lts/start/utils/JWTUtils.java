package com.lts.start.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtils {
    private static final String secretKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9"; //进行数字签名的私钥，一定要保管好，不能和我一样写到博客中。。。。。

    /**
     * 一个JWT实际上就是一个字符串，它由三部分组成，头部(Header)、载荷(Payload)与签名(Signature)
     */

    /**
     *生成串
     * @param account 就是微信小程序发来的code
     * @return
     * @throws Exception
     */
    public static String acquireJWT(String topic,String account)  {
        //生成jwt令牌
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(topic)//设置jwt主题
                .setIssuedAt(new Date())//设置jwt签发日期
                //.setExpiration(date)//设置jwt的过期时间
                .claim("account", account)
//              .claim("company", "itheima")
                .signWith(SignatureAlgorithm.HS256, secretKey);
        return jwtBuilder.compact();
    }

    /**
     * 解析JWT字符串
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) {
//        测试，对123进行加密之后在进行解密
        String token = JWTUtils.acquireJWT("用户账号","ob3ZA6ZUYHaC8I8kqNyeX1OLVW58");

        System.out.println(token);
    }
}
