package com.example.hk_ht.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.hk_ht.Service.IUserService;
import com.example.hk_ht.Util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private IUserService iUserService;
    @Value("${WChat.AppID}")
    private String appid;
    @Value("${WChat.AppSecret}")
    private String AppSecret;
    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";

    @NoAuth
    @RequestMapping("loginCode")
    public JsonResult<String> loginCode(String code, String encryptedData, String iv, HttpServletResponse response) throws IOException {
        String params = "appid=" + appid + "&secret=" + AppSecret + "&js_code=" + code + "&grant_type=" + "authorization_code";
        String url = "https://api.weixin.qq.com/sns/jscode2session?";
        String msg = HttpUtil.doGet(url, params);
        JSONObject convertvalue = (JSONObject) JSON.parse(msg);
        String openId = (String) convertvalue.get("openid");
        String sessionKey = (String) convertvalue.get("session_key");
        String Usertoken = iUserService.insOrFindWxUser(encryptedData, iv, sessionKey, openId);
        return new JsonResult<>(ok, Usertoken);

    }

    @RequestMapping("get")
    public JsonResult<String> get(HttpServletRequest request) {
        String token = request.getHeader("Authorize");
//        String idFromToken = jwtUtil.getIdFromToken(token); //string to int/it

        return new JsonResult<>(ok, "DSFSFSDF");
    }

}
