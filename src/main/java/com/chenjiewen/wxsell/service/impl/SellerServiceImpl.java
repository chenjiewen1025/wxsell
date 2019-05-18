package com.chenjiewen.wxsell.service.impl;


import com.chenjiewen.wxsell.VO.ShopList;
import com.chenjiewen.wxsell.dao.SellInfoDao;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sellerService")
public class SellerServiceImpl implements SellerService {


    @Resource
    private SellInfoDao sellInfoDao;


    @Override
    public void addSeller(SellerInfo sellerInfo) {
        sellInfoDao.addSeller(sellerInfo);
    }

    public SellerInfo selectById(String sellerId) {
        return sellInfoDao.selectById(sellerId);
    }

    @Override
    public void updateShopAble(SellerInfo sellerInfo) {
        sellInfoDao.updateShopAble(sellerInfo);
    }

    @Override
    public SellerInfo selectByUsername(String username) {
        return sellInfoDao.selectByUsername(username);
    }

    @Override
    public void updatePassword(SellerInfo sellerInfo) {
        sellInfoDao.updatePassword(sellerInfo);
    }

    @Override
    public void updateBase(SellerInfo sellerInfo) {
        sellInfoDao.updateBase(sellerInfo);
    }

    @Override
    public List<ShopList> getAll() {
        return sellInfoDao.getAll();
    }

    @Override
    public List<ShopList> getByCategory(String category) {
        return sellInfoDao.getByCategory(category);
    }
}
