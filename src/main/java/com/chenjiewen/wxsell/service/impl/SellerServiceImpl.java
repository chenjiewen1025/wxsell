package com.chenjiewen.wxsell.service.impl;


import com.chenjiewen.wxsell.dao.SellInfoDao;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sellerService")
public class SellerServiceImpl implements SellerService {


    @Resource
    private SellInfoDao sellInfoDao;


    public SellerInfo selectById(String sellerId) {
        return sellInfoDao.selectById(sellerId);
    }

    @Override
    public SellerInfo selectByUsername(String username) {
        return sellInfoDao.selectByUsername(username);
    }

    @Override
    public void updateBase(SellerInfo sellerInfo) {
        sellInfoDao.updateBase(sellerInfo);
    }
}
