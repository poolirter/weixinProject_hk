package com.example.hk_ht.Util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nocode tech
 */

public class NocodeOpenApiRequestDemo {
    @Value("${WChat.AppID}")
    private static String appid;


    public static void main(String[] args) {
        getRequestDemo();
        postRequestDemo();
    }

    /**
     * GET 请求示例
     */

    public static void getRequestDemo() {
        String url = "https://medical.nocode.com/open/v2/nc.ms.drug.generic.detail.get?drug_generic_id=7vBZEIcLZxj";
        String appId = "wx9699aebc28c0682a";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIzIiwiaXNzIjoic2VjcmV0IiwiZXhwIjoxNjk3NzEwNjM3LCJEYXRlIjoxNjk3NzEwNjM3fQ.TQEnRTa4FuvkDwd0Y6dM_G07sOMPkgzM0I-4fjlIGkk";
        String timestamp = String.valueOf(System.currentTimeMillis());

        HttpHeaders headers = new HttpHeaders();
        headers.add("appid", appId);
        headers.add("sign", DigestUtils.md5DigestAsHex((appId + token + timestamp).getBytes()));
        headers.add("timestamp", timestamp);
        System.out.println(headers);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, JsonNode.class);
        System.out.println("Response : " + exchange);
    }

    /**
     * POST 请求示例
     */

    public static void postRequestDemo() {
        String url = "https://medical.nocode.com/open/v2/nc.ms.smart.disease.recommend";

        //设置查询参数
        Map<String, String> body = new HashMap<>();
        body.put("content", "身体头疼");

        String appId = "REPLACE_YOUR_APP_ID";
        String token = "REPLACE_YOUR_TOKEN";
        String timestamp = String.valueOf(System.currentTimeMillis());

        HttpHeaders headers = new HttpHeaders();
        headers.add("appid", appId);
        headers.add("sign", DigestUtils.md5DigestAsHex((appId + token + timestamp).getBytes()));
        headers.add("timestamp", timestamp);
        headers.set("Content-Type", "application/json");
        HttpEntity<?> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        JsonNode jsonNode = restTemplate.postForObject(url, requestEntity, JsonNode.class);
        System.out.println("Response : " + jsonNode);
    }
}
