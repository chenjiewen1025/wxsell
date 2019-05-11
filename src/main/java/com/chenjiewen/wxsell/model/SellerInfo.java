package com.chenjiewen.wxsell.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SellerInfo {


    private String sellerId;

    private String shopName;

    private String shopImg;

    private String shopDes;

    private Integer shopStar;

    private Integer shopType;

    private Integer shopAble;

    private String username;

    private String password;

    private String openid;

    private Date createTime;

    private Date updateTime;

    public String getCreateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public String getShopDes() {
        return shopDes;
    }

    public void setShopDes(String shopDes) {
        this.shopDes = shopDes;
    }

    public Integer getShopStar() {
        return shopStar;
    }

    public void setShopStar(Integer shopStar) {
        this.shopStar = shopStar;
    }

    public Integer getShopType() {
        return shopType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

    public Integer getShopAble() {
        return shopAble;
    }

    public void setShopAble(Integer shopAble) {
        this.shopAble = shopAble;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
