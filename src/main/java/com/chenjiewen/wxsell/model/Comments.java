package com.chenjiewen.wxsell.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Comments {

    private String id;
    private String orderId;

    private String comment;

    private Integer star;

    private Date createTime;

    private Date updateTime;

    private String orderName;

    private String orderImg;

    public String getOrderImg() {

        return orderImg;
    }

    public void setOrderImg(String orderImg) {
        this.orderImg = orderImg;
    }

    public String getOrderName() {

        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String  getCreateTime() {
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
}
