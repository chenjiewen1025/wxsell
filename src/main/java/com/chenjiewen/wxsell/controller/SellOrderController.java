package com.chenjiewen.wxsell.controller;


import com.chenjiewen.wxsell.enums.ResultEnum;
import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/seller/order")

public class SellOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") int page,
                             @RequestParam(value = "size",defaultValue = "10") int size){
        PageInfo<OrderMaster> orderMasterList = orderService.selectAll(page,size);

        ModelAndView modelAndView = new ModelAndView("/order/list");
        modelAndView.addObject("orderMasterList",orderMasterList);
        return modelAndView;
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId){

        ModelAndView modelAndView = new ModelAndView();
        try {
            OrderMaster orderMaster = orderService.selectByOrderId(orderId);
            orderService.cancel(orderMaster);
        }
        catch (SellException e)
        {
            modelAndView.setViewName("common/error");
            modelAndView.addObject("msg", ResultEnum.ORDERDETAIL_NOT_EXIST.getMessage());
            modelAndView.addObject("url","/sell/seller/order/list");
            return modelAndView;
        }

        modelAndView.addObject("msg",ResultEnum.SUCCESS.getMessage());
        modelAndView.addObject("url","/sell/seller/order/list");
        modelAndView.setViewName("common/success");

        return modelAndView;

    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId){
        ModelAndView modelAndView = new ModelAndView();
        OrderMaster orderMaster = new OrderMaster();
        try {
            orderMaster = orderService.selectByOrderId(orderId);
        }
         catch (SellException e)
         {
             modelAndView.setViewName("common/error");
             modelAndView.addObject("msg", ResultEnum.ORDERDETAIL_NOT_EXIST.getMessage());
             modelAndView.addObject("url","/sell/seller/order/list");
             return modelAndView;
         }

         modelAndView.addObject("orderDTO",orderMaster);

        modelAndView.setViewName("order/detail");
        return  modelAndView;
    }

    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId){
        ModelAndView modelAndView = new ModelAndView();
        try {
            OrderMaster orderMaster = orderService.selectByOrderId(orderId);
            orderService.finish(orderMaster);
        }
        catch (SellException e)
        {
            modelAndView.setViewName("common/error");
            modelAndView.addObject("msg", ResultEnum.ORDERDETAIL_NOT_EXIST.getMessage());
            modelAndView.addObject("url","/sell/seller/order/list");
            return modelAndView;
        }

        modelAndView.addObject("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        modelAndView.addObject("url","/sell/seller/order/list");
        modelAndView.setViewName("common/success");

        return modelAndView;

    }

}
