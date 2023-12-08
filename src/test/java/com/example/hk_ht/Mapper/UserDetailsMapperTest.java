package com.example.hk_ht.Mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.hk_ht.Entity.WxUser;
import com.example.hk_ht.Util.JwtUtil;
import com.example.hk_ht.Util.NocodeOpenApiRequestDemo;
import com.example.hk_ht.Util.RedisUtil;
import com.example.hk_ht.Util.WxAESService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDetailsMapperTest {
    @Autowired
    private IUserMapper iUserMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;
//
//    @Autowired
//    private WxAESService wxAESService;
//    @Value("${encry}")
//    private String encry;
//    @Value("${vi}")
//    private String vi;
//    @Value("${key}")
//    private String key;


    @Test
    public void insert() {
        WxUser wxUser = new WxUser();
        wxUser.setOpenId("111");
        wxUser.setNickname("管理员");
        wxUser.setGender(0);
        wxUser.setSessionKey("123");
        System.out.println(iUserMapper.insertUser(wxUser));
    }

    @Test
    public void find() {
        WxUser user = iUserMapper.findUserByOpenId("111");
        System.out.println(user);
    }

    @Test
    public void sign() throws UnsupportedEncodingException {
        Date date = new Date(System.currentTimeMillis() + 1000 * 30 * 60);
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withClaim("uid", "3")
                .withClaim("Date", date)
                .withIssuer("secret")
                .withExpiresAt(date)
                .sign(algorithm);
        System.out.println(token);

    }

    @Test
    public void TEST() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIzIiwiaXNzIjoic2VjcmV0IiwiZXhwIjoxNjk1NjYxNzM2LCJEYXRlIjoxNjk1NjYxNzM2fQ.8jHt44D8yVcY_vyUwSyyQLM8PmyJbdSk3NytA8aTF54";
        DecodedJWT decode = JWT.decode(token);
        String payload = decode.getPayload();
        String uid = decode.getClaim("uid").asString();
        System.out.println(decode);
        System.out.println(payload);
        System.out.println(uid);
    }

    @Test
    public void verify() {
        String secret = "43dcbc5b-asd1-1234-123s-scv12347qwsa";

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiI2IiwiaXNzIjoiNDNkY2JjNWItYXNkMS0xMjM0LTEyM3Mtc2N2MTIzNDdxd3NhIiwiZXhwIjoxNjk1NjYwMTU4LCJEYXRlIjoxNjk1NjYwMTU4fQ.YpzF3rrP1jffM2llc8Eeh0BtBMo-zK4peghGfej7aAw";
        try {
            //验签
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt);
        } catch (Exception e) {//当传过来的token如果有问题,抛出异常
            System.out.println(e);
        }
    }

    @Test
    public void Test() {
        List<Integer> list=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        List<Integer> list3=new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list2.add(1);
        list2.add(1);
        list3.add(1);

        Map<String,List<Integer>> collection=new HashMap<>();
        collection.put("人",list);
        collection.put("动物",list2);
        collection.put("植物",list3);

        System.out.println(collection);



    }

//    @Test
//    public void TEST() {
//        String s = wxAESService.decryptWxData(encry, key, vi);
//        JSONObject convertvalue=new JSONObject();
//        convertvalue= (JSONObject) JSON.parse(s);
//        String nickName= (String) convertvalue.get("nickName");
//        Integer gender= (Integer) convertvalue.get("gender");
//        String avatarUrl= (String) convertvalue.get("avatarUrl");
//        System.out.println(nickName);
//        System.out.println(gender);
//        System.out.println(avatarUrl);
//    }
}