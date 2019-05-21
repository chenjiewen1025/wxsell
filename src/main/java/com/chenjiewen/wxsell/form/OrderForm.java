package com.chenjiewen.wxsell.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {
    /**
     * 买家姓名
     */

    private String name;

    /**
     * 买家手机号
     */

    private String phone;

    /**
     * 买家地址
     */

    private String address;

    /**
     * 买家微信openid
     */
    @NotBlank(message = "openid必填")
    private String openid;
    @NotBlank(message = "sellerId必填")
    private String sellerId;

    private String price;
    /**
     * 购物车
     */
    @NotBlank(message = "购物车不能为空")
    private String items;
}
