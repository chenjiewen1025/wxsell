package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.form.CategoryForm;
import com.chenjiewen.wxsell.model.ProductCategory;
import com.chenjiewen.wxsell.service.ProductCategoryService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/list")
    public ModelAndView list(){

        ModelAndView modelAndView = new ModelAndView("category/list");
        List<ProductCategory> productCategoryList = productCategoryService.selectAllProductCategory();
        modelAndView.addObject("categoryList",productCategoryList);

        return modelAndView;

    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId){

        ModelAndView modelAndView = new ModelAndView("category/index");
        if (categoryId != null)
        {
            ProductCategory productCategory = productCategoryService.selectByCategoryId(categoryId);
            modelAndView.addObject("category",productCategory);
        }
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form, BindingResult bindingResult){

        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors())
        {
            modelAndView.addObject("msg",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.addObject("url","/sell/seller/category/index");
            modelAndView.setViewName("common/error");
            return modelAndView;
        }

        ProductCategory productCategory = new ProductCategory();
        try {


        if (form.getCategoryId() != null)
        {
            productCategory = productCategoryService.selectByCategoryId(form.getCategoryId());

        BeanUtils.copyProperties(form,productCategory);

        productCategoryService.updateProductCategory(productCategory);
        }
        else
        {
            BeanUtils.copyProperties(form,productCategory);

            productCategoryService.addProductCategory(productCategory);
        }
        }
        catch (SellException e)
        {
            modelAndView.addObject("msg",e.getMessage());
            modelAndView.addObject("url","/sell/seller/category/index");
            modelAndView.setViewName("common/error");
        }

        modelAndView.setViewName("common/success");
        modelAndView.addObject("url","/sell/seller/category/list");
        return modelAndView;

    }

}
