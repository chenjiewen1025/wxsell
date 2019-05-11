package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.dao.SellerFormDao;
import com.chenjiewen.wxsell.model.SellerForm;
import com.chenjiewen.wxsell.service.SellerFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class SellerFormServiceImpl implements SellerFormService {

    @Resource
    private SellerFormDao dao;
    @Override
    public List<SellerForm> selectAll() {
        return dao.selectAll();
    }

    @Override
    public void add(SellerForm sellerForm) {
        dao.add(sellerForm);
    }
}
