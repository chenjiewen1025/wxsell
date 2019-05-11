package com.chenjiewen.wxsell.controller;


import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.form.ProductForm;
import com.chenjiewen.wxsell.model.ProductCategory;
import com.chenjiewen.wxsell.model.ProductInfo;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.ProductCategoryService;
import com.chenjiewen.wxsell.service.ProductInfoService;
import com.chenjiewen.wxsell.utils.KeyUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
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
    public ModelAndView list(HttpSession session,
                             @RequestParam(value = "type",required = false) String type){
        SellerInfo selelr = (SellerInfo) session.getAttribute("seller");


        List<ProductInfo> temp = new ArrayList<>();
        if ("0".equals(type))
        {
            temp = productInfoService.selectUpAll();
        }
        else if ("1".equals(type))
        {
            temp = productInfoService.selectDownAll();
        }
        else
        {
            temp = productInfoService.selectAll(selelr.getSellerId());
        }

        ModelAndView modelAndView = new ModelAndView("/product/list");
        String productInfoList = JSONArray.fromObject(temp).toString();
        modelAndView.addObject("productInfoList",productInfoList);
        return modelAndView;


    }

    @GetMapping("/on_sale")
    @CacheEvict(cacheNames = "product",key = "123")
    @ResponseBody
    public String onSale(@RequestParam("productId") String productId){
        try {
            productInfoService.onSale(productId);
        }
        catch (SellException e){

            return e.getMessage();
        }


        return "上架成功！";
    }

    @GetMapping("/off_sale")
    @CacheEvict(cacheNames = "product",key = "123")
    @ResponseBody
    public String offSale(@RequestParam("productId") String productId){

        try {
            productInfoService.offSale(productId);
        }
        catch (SellException e){

            return e.getMessage();
        }

        return "下架成功！";
    }

    @GetMapping("/index")
    public ModelAndView index(HttpSession session ,@RequestParam(value = "productId",required = false) String productId){
        SellerInfo seller = (SellerInfo) session.getAttribute("seller");
        ModelAndView modelAndView = new ModelAndView();
        if (!StringUtils.isEmpty(productId))
        {
            ProductInfo productInfo = productInfoService.selectByProductId(productId);
            modelAndView.addObject("productInfo",productInfo);

        }

        List<ProductCategory> categoryList = productCategoryService.selectAllProductCategory(seller.getSellerId());
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("product/index");
        return modelAndView;
    }


//    添加，修改商品
    @PostMapping("/save")
    @CacheEvict(cacheNames = "product",key = "123")
    @ResponseBody
    public String save(HttpSession session,
            @Valid ProductForm form,
                             BindingResult bindingResult
                             ){

        SellerInfo seller = (SellerInfo) session.getAttribute("seller");

        if (bindingResult.hasErrors())
        {

            return bindingResult.getFieldError().getDefaultMessage();
        }
        ProductInfo productInfo = new ProductInfo();
        productInfo.setSellerId(seller.getSellerId());
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

            return e.getMessage();
        }

        return "保存成功！";
    }

    @GetMapping("/delete")
    @CacheEvict(cacheNames = "product",key = "123")
    @ResponseBody
    public String delete(@RequestParam("productId") String productId){

        ModelAndView modelAndView = new ModelAndView();
        try {
            productInfoService.deleteById(productId);
        }
        catch (SellException e){
            return e.getMessage();
        }

        return "成功删除！";

    }

}

