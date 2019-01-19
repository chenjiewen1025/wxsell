package com.chenjiewen.wxsell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chenjiewen.wxsell.dao")
public class WxsellApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxsellApplication.class, args);
    }

}

