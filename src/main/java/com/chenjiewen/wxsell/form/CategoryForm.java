package com.chenjiewen.wxsell.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class CategoryForm {

    private String categoryId;

    /** 类目名字. */
    @NotBlank
    private String categoryName;


    private Integer categoryType;
}
