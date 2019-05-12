package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.model.AdminInfo;
import org.apache.ibatis.annotations.Param;

public interface AdminInfoService {
    AdminInfo selectByUsername( String username);
}
