package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.model.SellerForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellerFormService {
    List<SellerForm> selectAll();
    void add(SellerForm sellerForm);
    SellerForm selectById( String id);
    void updateDelFlagById(String id,Integer delFlag);
    void success(String id);
     String sendSuccessMess(String phone);
     String sendFailMess(String phone);
     String sendCodeMess(String code,String phone);
}
