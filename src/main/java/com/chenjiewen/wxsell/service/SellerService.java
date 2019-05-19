package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.VO.ShopList;
import com.chenjiewen.wxsell.model.SellerCategory;
import com.chenjiewen.wxsell.model.SellerInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SellerService {

    void addSeller(SellerInfo sellerInfo);
    SellerInfo selectById(String sellerId);
    void updateShopAble(SellerInfo sellerInfo);
    SellerInfo selectByUsername(String username);
    void updatePassword(SellerInfo sellerInfo);
    void updateBase(SellerInfo sellerInfo);
    List<ShopList> getAll();
    List<ShopList> getByCategory(String category);
    List<ShopList> getByKey(String key);

}
