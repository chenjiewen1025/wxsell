package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.model.User;
import com.chenjiewen.wxsell.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
     @RequestMapping("/show")
     @ResponseBody
    public  String show(){
         User user = userService.selectAll();
         String s = user.getName()+user.getId()+" ";
        return s;
    }

}
