package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.SellerInfo;
import org.apache.ibatis.annotations.Param;

public interface SellInfoDao {

    SellerInfo selectById(@Param("sellerId") String sellerId);
    SellerInfo selectByUsername(@Param("username") String username);
    void addSeller(SellerInfo sellerInfo);
    void updateBase(SellerInfo sellerInfo);
    void updatePassword(SellerInfo sellerInfo);
    void updateShopAble(SellerInfo sellerInfo);
}
