package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private OrderService orderService;
    @Test
    public void selec() {
       // List<OrderMaster> test = orderService.selectAll(1,7);
        //System.out.println(test.size());
    }
    @Test
    public void selectByBuyerOpenid() {
        //List<OrderMaster> test = orderMasterDao.selectAll();
       // System.out.println(test.size());
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