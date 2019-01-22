package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Resource
    private OrderMasterDao orderMasterDao;
    @Test
    public void selectByBuyerOpenid() {
        List<OrderMaster> test = orderMasterDao.selectByBuyerOpenid("1");
        System.out.println(test.size());
    }
    @Test
    public  void addOrderMasterTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1131");
        orderMaster.setBuyerAddress("3fds");
        orderMaster.setBuyerName("df");
        orderMaster.setBuyerOpenid("dfsfds");
        orderMaster.setBuyerPhone("dfs");
        orderMaster.setOrderStatus(3);
        orderMaster.setPayStatus(33);
        orderMaster.setOrderAmount(new BigDecimal(2.2));
        orderMasterDao.addOrderMaster(orderMaster);
    }
}