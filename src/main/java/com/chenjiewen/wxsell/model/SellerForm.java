package com.chenjiewen.wxsell.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SellerForm {


    private String id;

    private String shopName;

    private Integer shopType;

    private String username;

    private String password;

    private String shopImg1;

    private String shopImg2;

    private String personName;

    private String personId;

    private String personImg1;

    private String personImg2;

    private String phone;

    private String shopAddress;

    private Integer delFlag;

    private Date createTime;

    private Date updateTime;

    private String shopTypeName;

    public String getShopTypeName() {
        return shopTypeName;
    }

    public void setShopTypeName(String shopTypeName) {
        this.shopTypeName = shopTypeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getShopType() {
        return shopType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
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

    public String getShopImg1() {
        return shopImg1;
    }

    public void setShopImg1(String shopImg1) {
        this.shopImg1 = shopImg1;
    }

    public String getShopImg2() {
        return shopImg2;
    }

    public void setShopImg2(String shopImg2) {
        this.shopImg2 = shopImg2;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonImg1() {
        return personImg1;
    }

    public void setPersonImg1(String personImg1) {
        this.personImg1 = personImg1;
    }

    public String getPersonImg2() {
        return personImg2;
    }

    public void setPersonImg2(String personImg2) {
        this.personImg2 = personImg2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
