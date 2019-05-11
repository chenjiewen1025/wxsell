package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.model.SellerForm;

import java.util.List;

public interface SellerFormService {
    List<SellerForm> selectAll();
    void add(SellerForm sellerForm);
}
