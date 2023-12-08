package com.example.hk_ht.Service;

import com.example.hk_ht.Entity.UserDetails;
import com.example.hk_ht.Entity.WxUser;
import org.thymeleaf.standard.inline.StandardTextInliner;

import java.io.UnsupportedEncodingException;

public interface IUserService {
    /**
     * 添加或验证用户
     *
     * @param encryptedData
     * @param iv
     * @param sessionKey
     * @param openId
     * @return
     */
    String insOrFindWxUser(String encryptedData, String iv, String sessionKey, String openId) throws UnsupportedEncodingException;


}
