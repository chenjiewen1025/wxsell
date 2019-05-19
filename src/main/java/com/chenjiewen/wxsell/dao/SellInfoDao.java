package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.VO.ShopList;
import com.chenjiewen.wxsell.model.SellerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellInfoDao {

    SellerInfo selectById(@Param("sellerId") String sellerId);
    SellerInfo selectByUsername(@Param("username") String username);
    void addSeller(SellerInfo sellerInfo);
    void updateBase(SellerInfo sellerInfo);
    void updatePassword(SellerInfo sellerInfo);
    void updateShopAble(SellerInfo sellerInfo);
    List<ShopList> getAll();
    List<ShopList> getByCategory(@Param("category") String category);
    List<ShopList> getByKey(@Param("key") String key);

}
