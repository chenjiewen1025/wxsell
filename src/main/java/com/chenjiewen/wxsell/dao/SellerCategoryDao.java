package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.SellerCategory;
import com.chenjiewen.wxsell.model.SellerForm;

import java.util.List;

public interface SellerCategoryDao {

    List<SellerCategory> selectAll();

    void add(SellerCategory sellerCategory);
}
