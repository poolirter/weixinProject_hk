package com.example.hk_ht.Mapper;

import com.example.hk_ht.Entity.UserDetails;
import com.example.hk_ht.Entity.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IUserMapper {

    /**
     * 根据openid查询用户
     * @param openId
     * @return
     */
    WxUser findUserByOpenId(String openId);

    /**
     * 第一次进行注册用户
     * @param wxUser
     * @return
     */
    Integer insertUser(WxUser wxUser);
}
