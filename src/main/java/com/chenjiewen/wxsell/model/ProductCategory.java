package com.chenjiewen.wxsell.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class ProductCategory {


    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
    private Timestamp createTime;
    private Timestamp updateTime;
}
