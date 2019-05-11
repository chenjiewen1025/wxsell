package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.model.SellerCategory;
import com.chenjiewen.wxsell.model.SellerForm;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.SellerCategoryService;
import com.chenjiewen.wxsell.service.SellerFormService;
import com.chenjiewen.wxsell.service.SellerService;
import com.chenjiewen.wxsell.utils.KeyUtil;
import com.chenjiewen.wxsell.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/seller/apply")
public class SellerApplyController {

    @Autowired
    private SellerFormService sellerFormService;
    @Autowired
    private SellerService sellerService;

    @Autowired
    private SellerCategoryService sellerCategoryService;

    @RequestMapping("/index")
    public String index(Model model){
       List<SellerCategory> list =  sellerCategoryService.selectAll();
       model.addAttribute("list",list);
        return "apply/index";
    }


    @RequestMapping("/save")
    public String save(Model model , @Valid SellerForm form) throws Exception {
        form.setId(KeyUtil.genUniqueKey());

        form.setPassword(MD5.md5(form.getPassword(),"chenjiewen"));
        sellerFormService.add(form);
        model.addAttribute("msg","提交申请成功！！！请留意信息");
        model.addAttribute("url","/sell");
        return "common/success";
    }

    @RequestMapping("/isUse")
    @ResponseBody
    public String isUse(@RequestParam("username") String username){
        SellerInfo sellerInfo = sellerService.selectByUsername(username);
        if (sellerInfo==null)
        {
            return "1";
        }
        else
            return "0";
    }


}
