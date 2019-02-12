package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.ProductInfo;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellInfoDaoTest {

    @Resource
    private SellInfoDao sellInfoDao;

    @Test
    public void selectByOpenid() {

        SellerInfo sellerInfo = sellInfoDao.selectByOpenid("1");
        System.out.println("d");

    }
}