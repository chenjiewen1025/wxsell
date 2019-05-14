package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.constant.RedisConstant;
import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.model.SellerCategory;
import com.chenjiewen.wxsell.model.SellerForm;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.SellerCategoryService;
import com.chenjiewen.wxsell.service.SellerFormService;
import com.chenjiewen.wxsell.service.SellerService;
import com.chenjiewen.wxsell.utils.KeyUtil;
import com.chenjiewen.wxsell.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller/apply")
public class SellerApplyController {

    @Autowired
    private SellerFormService sellerFormService;
    @Autowired
    private SellerService sellerService;

    private static final int EXPIRE = RedisConstant.CODE_EXPIRE;  //验证码过期时间  10分钟

    private static final String PREFIX = RedisConstant.CODE_PREFIX;  //验证码 前缀

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Autowired
    private SellerCategoryService sellerCategoryService;



    @RequestMapping("/getCode")
    @ResponseBody
    public String code(@RequestParam("phone") String phone){

        String result = null;
        try {
            int temp = (int) ((Math.random()*9+1)*1000);
            String code = String.valueOf(temp);
            System.out.println(code);
            result =   sellerFormService.sendCodeMess(code,phone);
            String key = PREFIX+phone;
            redisTemplate.opsForValue().set(key,code,EXPIRE, TimeUnit.SECONDS);

        }catch (SellException e)
        {
            return "0";
        }

        return result;
    }


    @RequestMapping("/index")
    public String index(Model model){
       List<SellerCategory> list =  sellerCategoryService.selectAll();
       model.addAttribute("list",list);
        return "apply/index";
    }


    @RequestMapping("/save")
    public String save(Model model , @Valid SellerForm form) throws Exception {
        String key = PREFIX+form.getPhone();
        String code = redisTemplate.opsForValue().get(key);
        if (code.equals(form.getCode()))
        {
            form.setId(KeyUtil.genUniqueKey());
            form.setPassword(MD5.md5(form.getPassword(),"chenjiewen"));
            sellerFormService.add(form);
            model.addAttribute("msg","提交申请成功！！！请留意短信信息");
            model.addAttribute("url","/sell");
            return "common/success";
        }
        else {
            model.addAttribute("msg","验证码错误！");
            model.addAttribute("url","/sell/seller/apply/index");
            return "common/error";

        }




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
