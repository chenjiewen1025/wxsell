package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.AdminInfo;
import org.apache.ibatis.annotations.Param;

public interface AdminInfoDao {


   AdminInfo selectByUsername(@Param("username") String username);
}
