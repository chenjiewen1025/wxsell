package com.chenjiewen.wxsell.Handler;

import com.chenjiewen.wxsell.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {


    //拦截登录异常
    //http://sell.natapp4.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
      return new ModelAndView("redirect:http://127.0.0.1/sell/seller/index");
//        return new ModelAndView("redirect:/sell/seller/index");
    }
}
