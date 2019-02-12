package com.chenjiewen.wxsell.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class ProductForm {


    private String productId;

    /** 名字. */
    @NotNull(message = "必填")
    private String productName;

    /** 单价. */
    @NotNull(message = "必填")
    private BigDecimal productPrice;

    /** 库存. */
   // @NotEmpty(message = "必填")
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 类目编号. */
    private Integer categoryType;
}
