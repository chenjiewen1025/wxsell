package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.model.SellerCategory;
import com.chenjiewen.wxsell.model.SellerForm;

import java.util.List;

public interface SellerCategoryService {
    List<SellerCategory> selectAll();

    void add(SellerCategory sellerCategory);
}
