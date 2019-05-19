package com.chenjiewen.wxsell.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SellerCategory {

    private Integer value;

    private String id;

    private String name;

    private String img;

    private Date createTime;

    private Date updateTime;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
