package com.chenjiewen.wxsell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "messages")
public class MessageAccountConfig {
    private String AppID;
    private String AccountSid;
    private String AuthToken;
    private String RestURL;
    private String Able;
    private String Server;
    private String SuccessTp;
    private String FailTp;
    private String Code;

}
