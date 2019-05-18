package com.chenjiewen.wxsell.VO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BuyerOrderVo {

    private String img;

    private String name;

    private Integer status;

    private Double amount;

    private Date time;

    private String orderId;

    private Integer num;


    private Integer orderComment;

    public Integer getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(Integer orderComment) {
        this.orderComment = orderComment;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
