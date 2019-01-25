package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.OrderDetail;
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

public class OrderDetailDaoTest {

    @Resource
    private  OrderDetailDao orderDetailDao;
    @Test
    public void addOrderDetailTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1");
        orderDetail.setOrderId("1");
        orderDetail.setDetailId("222");
        orderDetail.setProductQuantity(22);
        orderDetail.setProductIcon("dfdf");
        orderDetail.setProductName("东方时尚");
        orderDetail.setProductPrice(new BigDecimal(3));
        //orderDetailDao.addOrderDetail(orderDetail);
        List<OrderDetail> orderDetailList = orderDetailDao.selectByOrderId("1");
    }
}