package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Resource
    private OrderService orderService;

    private  final String BUYER_OPENID = "abc";


    @Test
    public void create() {

    }

    @Test
    public void selectByOrderId() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}