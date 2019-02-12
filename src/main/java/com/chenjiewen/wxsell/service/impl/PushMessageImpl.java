package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.service.PushMessage;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushMessageImpl implements PushMessage {


    @Autowired
    private WxMpService wxMpService;

    @Override
    public void orderStatus(OrderMaster orderMaster,String mess) {

        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId("ZcYTIOgPP0NKtD_WXaZtHu0vMkV8HvA7w6X-kG14810");
        templateMessage.setToUser(orderMaster.getBuyerOpenid());

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", mess),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", "18868812345"),
                new WxMpTemplateData("keyword3", orderMaster.getOrderId()),
                new WxMpTemplateData("keyword4", orderMaster.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5", "￥" + orderMaster.getOrderAmount()),
                new WxMpTemplateData("remark", "欢迎再次光临！")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e) {
            log.error("【微信模版消息】发送失败, {}", e);
        }
    }


}
