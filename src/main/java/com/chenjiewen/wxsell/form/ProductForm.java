package com.chenjiewen.wxsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;


@Data
public class ProductForm {


    private String productId;

    /** 名字. */
    @NotEmpty(message = "必填")
    private String productName;

    /** 单价. */
    @NotEmpty(message = "必填")
    private BigDecimal productPrice;

    /** 库存. */
    @NotEmpty(message = "必填")
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 类目编号. */
    private Integer categoryType;
}
