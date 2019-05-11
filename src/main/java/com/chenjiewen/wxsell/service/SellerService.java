package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.model.SellerInfo;
import org.springframework.stereotype.Service;


public interface SellerService {


    SellerInfo selectById(String sellerId);

    SellerInfo selectByUsername(String username);

    void updateBase(SellerInfo sellerInfo);
}
