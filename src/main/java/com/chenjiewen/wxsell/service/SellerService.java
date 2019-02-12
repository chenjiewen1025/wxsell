package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.model.SellerInfo;
import org.springframework.stereotype.Service;


public interface SellerService {


    SellerInfo selectByOpenid(String openid);

    SellerInfo selectByUsername(String username);

}
