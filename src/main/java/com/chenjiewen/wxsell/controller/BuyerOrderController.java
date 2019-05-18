package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.VO.ResultVO;
import com.chenjiewen.wxsell.conterver.OrderForm2OrderMasterConverter;
import com.chenjiewen.wxsell.enums.OrderStatusEnum;
import com.chenjiewen.wxsell.enums.ResultEnum;
import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.form.OrderForm;
import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.OrderService;
import com.chenjiewen.wxsell.service.SellerService;
import com.chenjiewen.wxsell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    /**
     * 1.创建订单
     * 2.订单列表
     * 3.订单详情
     * 4取消订单
     */
    @Resource
    private OrderService orderService;
    @Resource
    private SellerService sellerService;

    @RequestMapping("/create")
    @ResponseBody
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("参数不正确", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderMaster orderMaster = OrderForm2OrderMasterConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderMaster.getOrderDetailList())) {
            log.error("购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderMaster result = orderService.create(orderMaster);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());

        return ResultVOUtil.success(map);
    }



    @RequestMapping("/detail")
    @ResponseBody
    public ResultVO<OrderMaster> detail(@RequestParam("openid") String openid,
                                        @RequestParam("orderId") String orderId) {
       
        OrderMaster orderMaster = orderService.selectByOrderId(orderId);
        if (orderMaster == null) {
            log.error("查不到该订单");
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        if (!orderMaster.getBuyerOpenid().equals(openid)) {
            log.error("查询订单不是本人");
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return ResultVOUtil.success(orderMaster);
    }

    @ResponseBody
    @RequestMapping("/cancel")
    public ResultVO cancel(@RequestParam("openId") String openId,
                           @RequestParam("orderId") String orderId) {
        OrderMaster orderMaster = orderService.selectByOrderId(orderId);
        if (orderMaster == null) {
            log.error("查不到该订单");
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        if (!orderMaster.getBuyerOpenid().equals(openId)) {
            log.error("查询订单不是本人");
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        orderService.cancel(orderMaster);
        return ResultVOUtil.success();

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

    @GetMapping("/print")
    public ModelAndView print(@RequestParam(value = "able",defaultValue = "1") String able,
                              @RequestParam("orderId") String orderId){
        ModelAndView modelAndView = new ModelAndView();
        OrderMaster orderMaster = new OrderMaster();
        SellerInfo sellerInfo = new SellerInfo();
        try {
            orderMaster = orderService.selectByOrderId(orderId);
            sellerInfo = sellerService.selectById(orderMaster.getSellerId());
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
        modelAndView.setViewName("buyer/orderDetail");
        return  modelAndView;
    }


}
