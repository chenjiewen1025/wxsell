package com.chenjiewen.wxsell.controller;


import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.model.ProductInfo;
import com.chenjiewen.wxsell.service.ProductInfoService;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") int page,
                             @RequestParam(value = "size",defaultValue = "10") int size){
        PageInfo<ProductInfo> productInfoPageInfo = productInfoService.selectAll(page,size);

        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("productInfoPageInfo",productInfoPageInfo);
        return modelAndView;


    }

    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId){
        ModelAndView modelAndView = new ModelAndView();
        try {
            productInfoService.onSale(productId);
        }
        catch (SellException e){
            modelAndView.addObject("msg",e.getMessage());
            modelAndView.addObject("url","/sell/seller/product/list");
            modelAndView.setViewName("common/error");
            return modelAndView;
        }


        modelAndView.addObject("url","/sell/seller/product/list");
        modelAndView.setViewName("common/success");
        return modelAndView;
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId){
        ModelAndView modelAndView = new ModelAndView();
        try {
            productInfoService.offSale(productId);
        }
        catch (SellException e){
            modelAndView.addObject("msg",e.getMessage());
            modelAndView.addObject("url","/sell/seller/product/list");
            modelAndView.setViewName("common/error");
            return modelAndView;
        }


        modelAndView.addObject("url","/sell/seller/product/list");
        modelAndView.setViewName("common/success");
        return modelAndView;
    }

}

