package com.chenjiewen.wxsell.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminInfo {

   private String id;

   private String username;

   private String password;

   private Date createTime;


   private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreateTime() {
        return  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
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
