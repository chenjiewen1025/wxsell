package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.model.OrderMaster;

public interface PushMessage {


    void orderStatus(OrderMaster orderMaster,String mess);


}
