package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.model.OrderMaster;

import java.util.List;

public interface OrderService {
    //创建订单
    OrderMaster create(OrderMaster orderMaster);
    //查询单个订单
    OrderMaster selectByOrderId(String orderId);
    //查询订单列表
    List<OrderMaster> selectAll();
    //取消订单
    OrderMaster cancel(OrderMaster orderMaster);
    //完结订单
    OrderMaster finish(OrderMaster orderMaster);
    //支付订单
    OrderMaster paid(OrderMaster orderMaster);

}
