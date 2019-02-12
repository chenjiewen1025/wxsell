package com.chenjiewen.wxsell.controller;


import com.chenjiewen.wxsell.constant.CookieConstant;
import com.chenjiewen.wxsell.constant.RedisConstant;
import com.chenjiewen.wxsell.enums.ResultEnum;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.SellerService;
import com.chenjiewen.wxsell.utils.CookieUtil;
import com.mysql.jdbc.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class LoginController {


    @Autowired
    private SellerService sellerService;


    @Autowired
    private StringRedisTemplate redisTemplate;


    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }



    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpServletResponse response){

        ModelAndView modelAndView = new ModelAndView();

        SellerInfo sellerInfo = sellerService.selectByUsername(username);

        if (sellerInfo==null)
        {
            modelAndView.addObject("msg","该用户不存在！！！");
            modelAndView.addObject("url","/sell/seller/index");
            modelAndView.setViewName("common/error");
            return modelAndView;
        }
        if (!password.equals(sellerInfo.getPassword()))
        {
            modelAndView.addObject("msg","密码错误！！！");
            modelAndView.addObject("url","/sell/seller/index");
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
        modelAndView.addObject("url","/sell/seller/order/list");
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
        modelAndView.addObject("url","/sell/seller/index");
        return modelAndView;
    }
}
