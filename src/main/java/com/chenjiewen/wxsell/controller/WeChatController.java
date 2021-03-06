package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.enums.ResultEnum;
import com.chenjiewen.wxsell.exception.SellException;
import com.google.common.base.Utf8;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.nio.cs.ext.GBK;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){

        String url = "http://chenjiewen.natapp1.cc/sell/wechat/userInfo";
        String result = null;
        try {
            result = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO,
                    URLEncoder.encode(returnUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("wechat网页获取code",result);
        log.info(returnUrl);
       return  "redirect:"+result;
    }

    @RequestMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl,
                           HttpSession attr){

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        WxMpUser user = new WxMpUser();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }

        log.info(returnUrl);
        String openId = wxMpOAuth2AccessToken.getOpenId();
            log.info(openId);
        String token = wxMpOAuth2AccessToken.getAccessToken();
        log.info(token);

        try {
             user =  wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,"zh_CN");

        } catch (WxErrorException e) {
            e.printStackTrace();
        }


        attr.setAttribute("openId", openId);
        attr.setAttribute("nickname", user.getNickname());
        attr.setAttribute("sexDesc", user.getSexDesc());
        attr.setAttribute("headingUrl", user.getHeadImgUrl());


        return "redirect:" + returnUrl;

    }
}
