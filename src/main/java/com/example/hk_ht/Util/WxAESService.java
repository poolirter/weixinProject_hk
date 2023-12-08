package com.example.hk_ht.Util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
@Slf4j
@Component
public class WxAESService {
    /**
     * 解密微信加密数据，对称解密使用的算法为 AES-128-CBC，数据采用PKCS#7填充。
     *
     * @param encryptedData 加密串
     * @param sessionKey    会话密钥
     * @param iv            解密算法初始向量
     * @return 解密后的数据
     */

    public String decryptWxData(String encryptedData, String sessionKey, String iv) {
        AES aes = new AES(Mode.CBC, Padding.NoPadding, Base64.decodeBase64(sessionKey),
                Base64.decodeBase64(iv));
        byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData));
        if (null != resultByte && resultByte.length > 0) {
            // 删除解密后明文的补位字符
            int padNum = resultByte[resultByte.length - 1];
            if (padNum < 1 || padNum > 32) {
                padNum = 0;
            }
            resultByte = Arrays.copyOfRange(resultByte, 0, resultByte.length - padNum);
            String result = new String(resultByte, StandardCharsets.UTF_8);
            log.info(">>>>> 微信加密数据解析结果:{}", result);
            return result;
        }
        return null;

    }
}
