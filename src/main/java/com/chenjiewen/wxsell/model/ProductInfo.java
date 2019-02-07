package com.chenjiewen.wxsell.model;

import com.chenjiewen.wxsell.enums.CodeEnum;
import com.chenjiewen.wxsell.enums.ProductStatusEnum;
import com.chenjiewen.wxsell.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class ProductInfo {
    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0正常1下架. */
    private Integer productStatus;

    /** 类目编号. */
    private Integer categoryType;

    private Timestamp createTime;

    private Timestamp updateTime;
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }


}
