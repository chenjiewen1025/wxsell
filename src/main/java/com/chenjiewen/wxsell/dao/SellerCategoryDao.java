package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.SellerCategory;
import com.chenjiewen.wxsell.model.SellerForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellerCategoryDao {

    List<SellerCategory> selectAll();
    void updateById(@Param("id") String id,@Param("name") String name);
    void add(SellerCategory sellerCategory);
    SellerCategory selectByValue(@Param("value") String value);
}
