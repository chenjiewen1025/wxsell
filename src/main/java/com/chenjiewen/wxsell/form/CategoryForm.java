package com.chenjiewen.wxsell.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    @NotBlank
    private String categoryName;

    /** 类目编号. */
    @NotNull
    private Integer categoryType;
}
