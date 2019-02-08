package com.chenjiewen.wxsell.controller;


import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.form.ProductForm;
import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.model.ProductCategory;
import com.chenjiewen.wxsell.model.ProductInfo;
import com.chenjiewen.wxsell.service.ProductCategoryService;
import com.chenjiewen.wxsell.service.ProductInfoService;
import com.chenjiewen.wxsell.utils.KeyUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") int page,
                             @RequestParam(value = "size",defaultValue = "5") int size,
                             @RequestParam(value = "type",required = false) String type){

        PageInfo<ProductInfo> productInfoPageInfo = new PageInfo<>();
        if ("0".equals(type))
        {
            productInfoPageInfo = productInfoService.selectUpAll(page,size);
        }
        else if ("1".equals(type))
        {
            productInfoPageInfo = productInfoService.selectDownAll(page,size);
        }
        else
        {
            productInfoPageInfo = productInfoService.selectAll(page,size);
        }

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

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId){

        ModelAndView modelAndView = new ModelAndView();
        if (!StringUtils.isEmpty(productId))
        {
            ProductInfo productInfo = productInfoService.selectByProductId(productId);
            modelAndView.addObject("productInfo",productInfo);

        }

        List<ProductCategory> categoryList = productCategoryService.selectAllProductCategory();
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("product/index");
        return modelAndView;
    }


//    添加，修改商品
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult
                             ){

        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors())
        {
            modelAndView.addObject("msg",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.addObject("url","/sell/seller/product/index");
            modelAndView.setViewName("common/error");
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            if (!StringUtils.isEmpty(form.getProductId()))
            {
                productInfo = productInfoService.selectByProductId(form.getProductId());
                BeanUtils.copyProperties(form,productInfo);
                productInfoService.update(productInfo);
            }
            else
            {
                form.setProductId(KeyUtil.genUniqueKey());
                BeanUtils.copyProperties(form,productInfo);
                productInfoService.addProductInfo(productInfo);
            }

        }
        catch (SellException e)
        {
            modelAndView.addObject("msg",e.getMessage());
            modelAndView.addObject("url","/sell/seller/product/index");
            modelAndView.setViewName("common/error");
        }
        modelAndView.addObject("url", "/sell/seller/product/list");
        modelAndView.setViewName("common/success");
        return modelAndView;
    }

}

