package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.dao.AdminInfoDao;
import com.chenjiewen.wxsell.model.AdminInfo;
import com.chenjiewen.wxsell.service.AdminInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Resource
    private AdminInfoDao dao;
    @Override
    public AdminInfo selectByUsername(String username) {


        return dao.selectByUsername(username);
    }
}
