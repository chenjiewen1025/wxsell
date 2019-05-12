package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.SellerForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellerFormDao {

    List<SellerForm> selectAll();

    void add(SellerForm sellerForm);

    SellerForm selectById(@Param("id") String id);

    void updateDelFlagById(@Param("id")String id,@Param("delFlag") Integer delFlag);
}
