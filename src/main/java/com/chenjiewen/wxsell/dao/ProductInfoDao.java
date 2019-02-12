package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductInfoDao {
    void addProductInfo(ProductInfo productInfo);
    void deleteById(@Param("productId") String id);
    ProductInfo selectByProductId(@Param("productId") String productId);
    List<ProductInfo> selectByProductStatus(@Param("productStatus") Integer productStatus);
    List<ProductInfo> selectAll();
    void updateProductInfo(ProductInfo productInfo);
}
