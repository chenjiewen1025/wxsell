package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.SellerService;
import com.chenjiewen.wxsell.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/seller")
public class SellerPasserController {

    @Autowired
    private SellerService sellerService;

    @RequestMapping("/password")
    public String password(HttpSession session, Model model){
        SellerInfo sellerInfo = (SellerInfo) session.getAttribute("seller");
        model.addAttribute("name",sellerInfo.getUsername());
        return "password/password";
    }

    @RequestMapping("/isUse")
    @ResponseBody
    public String isUse(@RequestParam("name") String username){
        SellerInfo sellerInfo = sellerService.selectByUsername(username);
        if (sellerInfo==null)
        {
            return "1";
        }
        else
            return "0";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam("name") String name,
                       @RequestParam("password") String password,
                       @RequestParam("oldpassword") String oldpassword,
                       HttpSession session) {

        SellerInfo seller = (SellerInfo) session.getAttribute("seller");
        SellerInfo temp = new SellerInfo();
        try {
            if (MD5.verify(oldpassword,"chenjiewen",seller.getPassword()))
            {
                temp.setSellerId(seller.getSellerId());
                temp.setUsername(name);
                temp.setPassword(MD5.md5(password,"chenjiewen"));
                sellerService.updatePassword(temp);
                return "1";
            }
            else {
                return "0";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "2";
        }

    }

}
