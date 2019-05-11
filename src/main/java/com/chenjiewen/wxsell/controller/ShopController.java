package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.form.ProductForm;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.SellerService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/seller/shop")
public class ShopController {

    @Autowired
    private SellerService sellerService;

    @RequestMapping("/list")
    public String list(Model model, HttpSession session){

        SellerInfo seller = (SellerInfo) session.getAttribute("seller");
        seller.setPassword(null);
        model.addAttribute("seller",seller);
        return "shop/list";
    }

    @RequestMapping("/edit")
    public String edit(Model model, HttpSession session){
        SellerInfo seller = (SellerInfo) session.getAttribute("seller");
        seller.setPassword(null);
        model.addAttribute("seller",seller);
        return "shop/form";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(HttpSession session,@Valid SellerInfo form){
        SellerInfo seller = (SellerInfo) session.getAttribute("seller");
        seller.setShopName(form.getShopName());
        seller.setShopDes(form.getShopDes());
        seller.setShopAble(form.getShopAble());
        seller.setShopImg(form.getShopImg());
        seller.setShopType(form.getShopType());
        try {
            sellerService.updateBase(seller);
        }
        catch (SellException e)
        {
            return e.getMessage();
        }

        session.setAttribute("seller",seller);
        return "修改成功！";
    }
}