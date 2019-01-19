package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.dao.UserDao;
import com.chenjiewen.wxsell.model.User;
import com.chenjiewen.wxsell.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Override
    public User selectAll() {
        return userDao.selectAll();
    }
}
