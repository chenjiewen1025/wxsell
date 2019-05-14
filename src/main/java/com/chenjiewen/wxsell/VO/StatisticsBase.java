package com.chenjiewen.wxsell.VO;


import lombok.Data;


public class StatisticsBase {

    private Integer orderNum;

    private Integer detailNum;

    private Double amount;

    private Double orderAvg;

    private Double detailAvg;

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getDetailNum() {
        return detailNum;
    }

    public void setDetailNum(Integer detailNum) {
        this.detailNum = detailNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getOrderAvg() {
        if (orderNum==null||amount==null)
        {
            return Double.valueOf(0);
        }
        else {
            return amount/orderNum;
        }

    }

    public void setOrderAvg(Double orderAvg) {
        this.orderAvg = orderAvg;
    }

    public Double getDetailAvg() {
        if (detailNum==null||amount==null)
        {
            return Double.valueOf(0);
        }
        else {
            return amount/detailNum;
        }

    }

    public void setDetailAvg(Double detailAvg) {
        this.detailAvg = detailAvg;
    }
}
