package com.chenjiewen.wxsell.enums;


import lombok.Getter;

//商品状态
@Getter
public enum ProductStatusEnum {

    UP(0,"在架"),
    DOWN(1,"下架")
    ;


    private  int code;

    private  String message;

    ProductStatusEnum(int code,String message) {
        this.code = code;
        this.message = message;
    }
}
