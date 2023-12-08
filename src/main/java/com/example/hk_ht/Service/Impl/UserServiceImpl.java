package com.example.hk_ht.Service.Impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.hk_ht.Entity.WxUser;
import com.example.hk_ht.Mapper.IUserMapper;
import com.example.hk_ht.Service.Ex.InsertException;
import com.example.hk_ht.Service.IUserService;
import com.example.hk_ht.Util.JsonResult;
import com.example.hk_ht.Util.JwtUtil;
import com.example.hk_ht.Util.RedisUtil;
import com.example.hk_ht.Util.WxAESService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper iUserMapper;
    @Autowired
    private WxAESService wxAESService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public String insOrFindWxUser(String encryptedData, String iv, String sessionKey, String openId) throws UnsupportedEncodingException {
        WxUser result = iUserMapper.findUserByOpenId(openId);
        if (result == null) {
            String userMsg = wxAESService.decryptWxData(encryptedData, sessionKey, iv);
            JSONObject convertValue = (JSONObject) JSON.parse(userMsg);
            String nickName = (String) convertValue.get("nickName");
            Integer gender = (Integer) convertValue.get("gender");
            String avatarUrl = (String) convertValue.get("avatarUrl");
            WxUser wxUser = new WxUser();
            wxUser.setOpenId(openId);
            wxUser.setSessionKey(sessionKey);
            wxUser.setNickname(nickName);
            wxUser.setAvatarUrl(avatarUrl);
            wxUser.setGender(gender);
            Integer rows = iUserMapper.insertUser(wxUser);
            if (rows != 1) {
                throw new InsertException("插入时产生未知异常");
            }

            String insToken = jwtUtil.sign(wxUser.getUid().toString());
            wxUser.setToken(insToken);
            System.out.println(wxUser.getToken());

            redisUtil.set(wxUser.getUid().toString(), insToken);
            redisUtil.expire(wxUser.getUid().toString(), 30);
            System.out.println(redisUtil.getExpire(wxUser.getUid().toString()));
            return insToken;
        }

        String UserToken = jwtUtil.sign(result.getUid().toString());
        result.setToken(UserToken);
        redisUtil.set(result.getUid().toString(), UserToken);
        redisUtil.expire(result.getUid().toString(), 30);
        System.out.println(redisUtil.getExpire(result.getUid().toString()));
        return UserToken;


    }


}
