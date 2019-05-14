package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.model.SellerInfo;
import org.springframework.stereotype.Service;


public interface SellerService {

    void addSeller(SellerInfo sellerInfo);
    SellerInfo selectById(String sellerId);
    void updateShopAble(SellerInfo sellerInfo);
    SellerInfo selectByUsername(String username);
    void updatePassword(SellerInfo sellerInfo);
    void updateBase(SellerInfo sellerInfo);
}
