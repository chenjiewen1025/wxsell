package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.dao.OrderMasterDao;
import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.service.PayService;
import com.chenjiewen.wxsell.service.PushMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PushMessage pushMessage;


    @Override
    public void create(OrderMaster orderMaster,String mess) {

        //todo  支付
        pushMessage.orderStatus(orderMaster,mess);

    }
}
