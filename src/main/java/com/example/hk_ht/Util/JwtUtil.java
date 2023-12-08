package com.example.hk_ht.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.hk_ht.Entity.UserDetails;
import com.example.hk_ht.Entity.WxUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Component
//JWT Token工具类
public class JwtUtil {
    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";

    //生成时间key
    private static final String CLAIM_KEY_CREATED = "created";
    //密钥
    private final String secret = "43dcbc5b-asd1-1234-123s-scv12347qwsa";
    //失效时间
    private Long expiration = Long.valueOf(60 * 120 * 1000);

    public String sign(String id) throws UnsupportedEncodingException {
        Date date = new Date(System.currentTimeMillis() + expiration);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("uid", id)
                .withClaim("Date", date)
                .withIssuer(secret)
                .withExpiresAt(date)
                .sign(algorithm);

    }

    public boolean verifyToken(String token) {
        try {
            //验签
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {//当传过来的token如果有问题,抛出异常
            return false;
        }

    }


    //生成token失效时间
    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    //从token中获取Id
    public String getIdFromToken(String token) {
        DecodedJWT decode = JWT.decode(token);
        String uid = decode.getClaim("uid").asString();
        return uid;
    }

    //从jwt中获荷载
    public Claims getClaimsFormToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    //刷新token
    public String refreshToken(String token) throws UnsupportedEncodingException {
        String idFromToken = getIdFromToken(token);
        return sign(idFromToken);
    }

}




