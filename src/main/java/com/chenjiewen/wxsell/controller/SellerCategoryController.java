package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.form.CategoryForm;
import com.chenjiewen.wxsell.model.ProductCategory;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.ProductCategoryService;
import com.chenjiewen.wxsell.utils.KeyUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/list")
    public ModelAndView list(HttpSession session){
        SellerInfo seller = (SellerInfo) session.getAttribute("seller");
        ModelAndView modelAndView = new ModelAndView("category/list");
        List<ProductCategory> temp = productCategoryService.selectAllProductCategory(seller.getSellerId());
        String productCategoryList = JSONArray.fromObject(temp).toString();
        modelAndView.addObject("categoryList",productCategoryList);
        return modelAndView;

    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) String categoryId){

        ModelAndView modelAndView = new ModelAndView("category/index");
        if (categoryId != null )
        {
            ProductCategory productCategory = productCategoryService.selectByCategoryId(categoryId);
            modelAndView.addObject("category",productCategory);
        }
        return modelAndView;
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(@Valid CategoryForm form, BindingResult bindingResult,
                       HttpSession session){


        if (bindingResult.hasErrors())
        {

            return bindingResult.getFieldError().getDefaultMessage();
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
            SellerInfo sellerInfo = (SellerInfo) session.getAttribute("seller");
            BeanUtils.copyProperties(form,productCategory);
            productCategory.setCategoryId(KeyUtil.genUniqueKey());
            productCategory.setSellerId(sellerInfo.getSellerId());
            productCategoryService.addProductCategory(productCategory);
        }
        }
        catch (SellException e)
        {
            return e.getMessage();
        }


        return "保存成功！";

    }

}
