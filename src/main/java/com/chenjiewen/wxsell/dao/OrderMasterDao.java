package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.OrderMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMasterDao {
    List<OrderMaster> selectByBuyerOpenid(@Param("buyerOpenid") String buyerOpenid);
    OrderMaster selectByOrderId(@Param("orderId") String orderId);
    void addOrderMaster(OrderMaster orderMaster);
    List<OrderMaster> selectAll();
}
