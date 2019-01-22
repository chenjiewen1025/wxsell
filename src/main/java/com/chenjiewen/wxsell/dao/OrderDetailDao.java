package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailDao {
    List<OrderDetail> selectByOrderId(@Param("orderId") String orderId);
    void addOrderDetail(OrderDetail orderDetail);
}
