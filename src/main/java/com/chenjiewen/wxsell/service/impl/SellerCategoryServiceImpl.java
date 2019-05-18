package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.dao.SellerCategoryDao;
import com.chenjiewen.wxsell.model.SellerCategory;
import com.chenjiewen.wxsell.service.SellerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class SellerCategoryServiceImpl implements SellerCategoryService {

    @Resource
    private SellerCategoryDao dao;
    @Override
    public List<SellerCategory> selectAll() {
        return dao.selectAll();
    }

    @Override
    public void updateById(String id, String name) {
        dao.updateById(id,name);
    }

    @Override
    public void add(SellerCategory sellerCategory) {
        dao.add(sellerCategory);
    }

    @Override
    public SellerCategory selectByValue(String value) {
        return dao.selectByValue(value);
    }
}
