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


    public SellerInfo selectByOpenid(String openid) {
        return sellInfoDao.selectByOpenid(openid);
    }

    @Override
    public SellerInfo selectByUsername(String username) {
        return sellInfoDao.selectByUsername(username);
    }
}
