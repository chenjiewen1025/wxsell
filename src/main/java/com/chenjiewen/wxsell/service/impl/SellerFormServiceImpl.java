package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.constant.ApplyConstant;
import com.chenjiewen.wxsell.dao.SellerFormDao;
import com.chenjiewen.wxsell.model.SellerForm;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.SellerFormService;
import com.chenjiewen.wxsell.service.SellerService;
import com.chenjiewen.wxsell.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
public class SellerFormServiceImpl implements SellerFormService {

    @Resource
    private SellerFormDao dao;

    @Autowired
    private SellerService sellerService;

    @Override
    public List<SellerForm> selectAll() {
        return dao.selectAll();
    }

    @Override
    public void add(SellerForm sellerForm) {
        dao.add(sellerForm);
    }

    @Override
    public SellerForm selectById(String id) {
        return dao.selectById(id);
    }

    @Override
    public void updateDelFlagById(String id, Integer delFlag) {
        dao.updateDelFlagById(id,delFlag);
    }


    @Transactional
    public void success(String id) {
        dao.updateDelFlagById(id, ApplyConstant.success);
        SellerForm temp = dao.selectById(id);
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setShopType(temp.getShopType());
        sellerInfo.setShopName(temp.getShopName());
        sellerInfo.setUsername(temp.getUsername());
        sellerInfo.setPassword(temp.getPassword());
        sellerService.addSeller(sellerInfo);
    }
}
