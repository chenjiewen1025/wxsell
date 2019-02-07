package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.enums.ResultEnum;
import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl){

        OrderMaster orderMaster = orderService.selectByOrderId(orderId);
        if (orderMaster==null)
        {
            throw new  SellException(ResultEnum.ORDER_NOT_EXIST);
        }
            //发起支付
        orderService.paid(orderMaster);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pay/create");
        modelAndView.addObject("returnUrl",returnUrl);
        return modelAndView;
    }
}
