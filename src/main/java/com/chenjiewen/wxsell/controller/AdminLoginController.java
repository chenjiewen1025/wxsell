package com.chenjiewen.wxsell.controller;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.chenjiewen.wxsell.constant.ApplyConstant;
import com.chenjiewen.wxsell.constant.CookieConstant;
import com.chenjiewen.wxsell.constant.RedisConstant;
import com.chenjiewen.wxsell.enums.ResultEnum;
import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.model.AdminInfo;
import com.chenjiewen.wxsell.model.SellerCategory;
import com.chenjiewen.wxsell.model.SellerForm;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.AdminInfoService;
import com.chenjiewen.wxsell.service.SellerCategoryService;
import com.chenjiewen.wxsell.service.SellerFormService;
import com.chenjiewen.wxsell.service.SellerService;
import com.chenjiewen.wxsell.utils.CookieUtil;
import com.chenjiewen.wxsell.utils.KeyUtil;
import com.chenjiewen.wxsell.utils.MD5;
import net.sf.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {



    @Autowired
    private AdminInfoService adminInfoService;


    @Autowired
    private SellerFormService sellerFormService;


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SellerCategoryService sellerCategoryService;


    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("adminIndex");
    }


    @RequestMapping("/list")
    public String list(Model model){
        List<SellerForm> temp = sellerFormService.selectAll();
        String list  = JSONArray.fromObject(temp).toString();
        model.addAttribute("list",list);
        return "admin/list";
    }

    @RequestMapping("/category")
    public String category(Model model){
        List<SellerCategory> temp = sellerCategoryService.selectAll();
        String list  = JSONArray.fromObject(temp).toString();
        model.addAttribute("list",list);
        return "admin/category";
    }


    @RequestMapping("/edit")
    public String edit(Model model,@RequestParam(value = "id" ,required = false) String id,
                       @RequestParam(value = "name",required = false) String name){
        model.addAttribute("name",name);
        model.addAttribute("id",id);
        return "admin/edit";
    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam(value = "id" ,required = false) String id,
                       @RequestParam(value = "name",required = false) String name){
        try {
            if (id==null || "".equals(id))
            {
                SellerCategory temp = new SellerCategory();
                temp.setId(KeyUtil.genUniqueKey());
                temp.setName(name);
                sellerCategoryService.add(temp);
            }
            else {
                sellerCategoryService.updateById(id,name);
            }
        }catch (Exception e)
        {
            return "0";
        }

        return "1";
    }

    @RequestMapping("/detail")
    public String detail(Model model,@RequestParam("id") String id ){
        SellerForm sellerForm = sellerFormService.selectById(id);

        model.addAttribute("sellerForm",sellerForm);
        return "admin/detail";
    }



    @RequestMapping("/success")
    @ResponseBody
    public String success(@RequestParam("id") String id,@RequestParam("phone") String phone){

        try {

            sellerFormService.success(id);

        }catch (SellException e)
        {
            return "0";
        }


          String result =   sellerFormService.sendSuccessMess(phone);

        return result;
    }

    @RequestMapping("/fail")
    @ResponseBody
    public String fail(@RequestParam("id") String id,@RequestParam("phone") String phone ){

        try {
            sellerFormService.updateDelFlagById(id,ApplyConstant.fail);
        }catch (SellException e)
        {
            return "0";
        }

        String result = sellerFormService.sendFailMess(phone);


        return result;
    }




    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpServletResponse response){

        ModelAndView modelAndView = new ModelAndView();

        AdminInfo adminInfo = adminInfoService.selectByUsername(username);

        if (adminInfo==null)
        {
            modelAndView.addObject("msg","该用户不存在！！！");
            modelAndView.addObject("url","/sell/admin/index");
            modelAndView.setViewName("common/error");
            return modelAndView;
        }



        boolean istrue = false;
        try {
            istrue = MD5.verify(password,"chenjiewen",adminInfo.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!istrue)
        {
            modelAndView.addObject("msg","密码错误！！！");
            modelAndView.addObject("url","/sell/admin/index");
            modelAndView.setViewName("common/error");
            return modelAndView;
        }

        //token set redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        String key = String.format(RedisConstant.TOKEN_PREFIX,token);
        redisTemplate.opsForValue().set(key,username,expire, TimeUnit.SECONDS);

        //token set cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);

        modelAndView.addObject("msg","登录成功！！！");
        modelAndView.addObject("url","/sell/admin/list");
        modelAndView.setViewName("common/success");
        return modelAndView;



    }

    @GetMapping("/logout")
    public ModelAndView login(HttpServletRequest request,
                              HttpServletResponse response
    ){
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if (cookie!=null)
        {
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        ModelAndView modelAndView = new ModelAndView("common/success");
        modelAndView.addObject("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        modelAndView.addObject("url","/sell/admin/index");
        return modelAndView;
    }

}
