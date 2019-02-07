package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.model.OrderDetail;
import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Resource
    private OrderService orderService;

    private  final String BUYER_OPENID = "abc";


    @Test
    public void create() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerName("cjw");
        orderMaster.setBuyerAddress("kkk");
        orderMaster.setBuyerPhone("16722829272");
        orderMaster.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        OrderDetail o2 = new OrderDetail();
        o1.setProductId("123");
        o1.setProductQuantity(1);
        o2.setProductId("21");
        o2.setProductQuantity(3);
        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderMaster.setOrderDetailList(orderDetailList);

       OrderMaster result = orderService.create(orderMaster);
       System.out.println(result);
    }

    @Test
    public void selectByOrderId() {
       OrderMaster orderMaster = orderService.selectByOrderId("15484102680321943323");
    }

    @Test
    public void selectAll() {
       // List<OrderMaster> orderMasters = orderService.selectAll(1,5);
    }

    @Test
    public void cancel() {
        OrderMaster orderMaster = orderService.selectByOrderId("15484102680321943323");

      OrderMaster tes  = orderService.cancel(orderMaster);
    }

    @Test
    public void finish() {
        OrderMaster orderMaster = orderService.selectByOrderId("15484102680321943323");
        OrderMaster test = orderService.finish(orderMaster);
    }

    @Test
    public void paid() {
        OrderMaster orderMaster = orderService.selectByOrderId("15484102680321943323");
        OrderMaster test = orderService.paid(orderMaster);
    }
}