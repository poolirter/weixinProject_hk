package com.example.hk_ht.Entity;

import java.io.Serializable;
import java.util.Objects;

public class UserSecret implements Serializable {
    private String openId;
    private Integer uid;
    private String sessionKey;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSecret)) return false;
        UserSecret that = (UserSecret) o;
        return Objects.equals(getOpenId(), that.getOpenId()) && Objects.equals(getUid(), that.getUid()) && Objects.equals(getSessionKey(), that.getSessionKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOpenId(), getUid(), getSessionKey());
    }

    @Override
    public String toString() {
        return "UserSecret{" +
                "openId='" + openId + '\'' +
                ", uid=" + uid +
                ", sessionKey='" + sessionKey + '\'' +
                '}';
    }
}
