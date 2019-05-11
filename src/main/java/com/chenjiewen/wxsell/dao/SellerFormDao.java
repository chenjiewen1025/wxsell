package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.SellerForm;

import java.util.List;

public interface SellerFormDao {

    List<SellerForm> selectAll();

    void add(SellerForm sellerForm);
}
