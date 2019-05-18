package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.ProductInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductInfoDao {
    void addProductInfo(ProductInfo productInfo);
    void deleteById(@Param("productId") String id);
    ProductInfo selectByProductId(@Param("productId") String productId);
    List<ProductInfo> selectByProductStatus(@Param("productStatus") Integer productStatus,
                                            @Param("sellerId") String sellerId);
    List<ProductInfo> selectAll(@Param("sellerId") String sellerId);
    void updateProductInfo(ProductInfo productInfo);
}
