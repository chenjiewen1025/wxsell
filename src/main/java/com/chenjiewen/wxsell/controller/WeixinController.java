package com.chenjiewen.wxsell.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
@Slf4j
@RequestMapping("/weixin")
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) throws MalformedURLException {
      log.info("进入auth");
      log.info("code"+code);


      String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxef9ffe8df044941e&secret=8608baea6b73936076e3dd61684fca97&code="+code+"&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
        log.info("re:"+response);
    }
}
