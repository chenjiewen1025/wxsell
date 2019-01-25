package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.VO.ResultVO;
import com.chenjiewen.wxsell.conterver.OrderForm2OrderMasterConverter;
import com.chenjiewen.wxsell.enums.OrderStatusEnum;
import com.chenjiewen.wxsell.enums.ResultEnum;
import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.form.OrderForm;
import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.service.OrderService;
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

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    /**
     *1.创建订单
     * 2.订单列表
     * 3.订单详情
     * 4取消订单
     *
     */
    @Resource
    private OrderService orderService;

    @RequestMapping("/create")
    @ResponseBody
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if (bindingResult.hasErrors())
        {
         log.error("参数不正确",orderForm);
         throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                 bindingResult.getFieldError().getDefaultMessage());
        }

        OrderMaster orderMaster = OrderForm2OrderMasterConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderMaster.getOrderDetailList()))
        {
            log.error("购物车不能为空");
            throw  new SellException(ResultEnum.CART_EMPTY);
        }

       OrderMaster result =  orderService.create(orderMaster);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());

        return ResultVOUtil.success(map);
    }


    @GetMapping("/list")
    @ResponseBody
    public ResultVO<List<OrderMaster>> list(@RequestParam("openid") String openid,
                                            @RequestParam(value = "page",defaultValue = "0") Integer page,
                                            @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid))
        {
            log.error("openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        return ResultVOUtil.success(orderService.selectByBuyerOpenid(openid));

    }
}
