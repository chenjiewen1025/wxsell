package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.SellerInfo;
import org.apache.ibatis.annotations.Param;

public interface SellInfoDao {

    SellerInfo selectByOpenid(@Param("openid") String openid);
    SellerInfo selectByUsername(@Param("username") String username);
}
