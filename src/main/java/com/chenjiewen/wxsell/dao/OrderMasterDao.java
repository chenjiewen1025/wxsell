package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.OrderMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMasterDao {
    List<OrderMaster> selectByBuyerOpenid(@Param("buyerOpenid") String buyerOpenid);
    void addOrderMaster(OrderMaster orderMaster);
}
