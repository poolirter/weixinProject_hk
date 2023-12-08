package com.example.hk_ht.Entity;

import java.io.Serializable;
import java.util.Objects;

public class WxUser implements Serializable {
    private Integer uid;
    private String openId;
    private String sessionKey;
    private String username;
    private String nickname;
    private String avatarUrl;
    private Integer gender;
    private String city;
    private String province;
    private String country;
    private String token;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WxUser)) return false;
        WxUser wxUser = (WxUser) o;
        return Objects.equals(getUid(), wxUser.getUid()) && Objects.equals(getOpenId(), wxUser.getOpenId()) && Objects.equals(getSessionKey(), wxUser.getSessionKey()) && Objects.equals(getUsername(), wxUser.getUsername()) && Objects.equals(getNickname(), wxUser.getNickname()) && Objects.equals(getAvatarUrl(), wxUser.getAvatarUrl()) && Objects.equals(getGender(), wxUser.getGender()) && Objects.equals(getCity(), wxUser.getCity()) && Objects.equals(getProvince(), wxUser.getProvince()) && Objects.equals(getCountry(), wxUser.getCountry()) && Objects.equals(getToken(), wxUser.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getOpenId(), getSessionKey(), getUsername(), getNickname(), getAvatarUrl(), getGender(), getCity(), getProvince(), getCountry(), getToken());
    }

    @Override
    public String toString() {
        return "WxUser{" +
                "uid=" + uid +
                ", openId='" + openId + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", gender=" + gender +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
