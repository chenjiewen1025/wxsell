package com.chenjiewen.wxsell.controller;


import com.chenjiewen.wxsell.model.SellerInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/seller/order")

public class SellOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/list")
    public  ModelAndView list(HttpSession session,
                              @RequestParam(value = "phone",defaultValue = "") String phone,
                             @RequestParam(value = "orderstatus",defaultValue = "3") Integer status){
        SellerInfo seller = (SellerInfo) session.getAttribute("seller");

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setSellerId(seller.getSellerId());
        orderMaster.setBuyerPhone(phone);
        orderMaster.setOrderStatus(status);
        List<OrderMaster> temp = orderService.selectAll(orderMaster);
        ModelAndView modelAndView = new ModelAndView("/order/list");
        String orderMasterList = JSONArray.fromObject(temp).toString();
        modelAndView.addObject("orderMasterList",orderMasterList);
        modelAndView.addObject("phone",phone);
        modelAndView.addObject("orderstatus",status);
        return modelAndView;
    }


    @GetMapping("/cancel")
    @ResponseBody
    public String cancel(@RequestParam("orderId") String orderId){

        try {
            OrderMaster orderMaster = orderService.selectByOrderId(orderId);
            orderService.cancel(orderMaster);
        }
        catch (SellException e)
        {
            return ResultEnum.ORDERDETAIL_NOT_EXIST.getMessage();
        }


        return ResultEnum.SUCCESS.getMessage();

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
    @GetMapping("/print")
    public ModelAndView print(@RequestParam(value = "able",defaultValue = "1") String able,HttpSession session,@RequestParam("orderId") String orderId){
        ModelAndView modelAndView = new ModelAndView();
        OrderMaster orderMaster = new OrderMaster();
        SellerInfo sellerInfo = (SellerInfo) session.getAttribute("seller");
        sellerInfo.setPassword(null);
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
        modelAndView.addObject("able",able);
        modelAndView.addObject("orderDTO",orderMaster);
        modelAndView.addObject("seller",sellerInfo);
        modelAndView.setViewName("order/print");
        return  modelAndView;
    }
    @GetMapping("/finish")
    @ResponseBody
    public String finished(@RequestParam("orderId") String orderId){
        try {
            OrderMaster orderMaster = orderService.selectByOrderId(orderId);
            orderService.finish(orderMaster);
        }
        catch (SellException e)
        {
            return  ResultEnum.ORDERDETAIL_NOT_EXIST.getMessage();
        }

        return ResultEnum.ORDER_FINISH_SUCCESS.getMessage();

    }

}
