package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.dto.CarDTO;
import com.chenjiewen.wxsell.model.ProductInfo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductInfoService {
    ProductInfo selectByProductId(String productId);


    PageInfo<ProductInfo> selectAll(int page,int size);


    List<ProductInfo> selectUpAll();  //在架
    void deleteById(String id);
    void addProductInfo(ProductInfo productInfo);
    //加库存
    void increaseStock(List<CarDTO> carDTOList);
    //减库存
    void decreaseStock(List<CarDTO> carDTOList);

    //上架
    void onSale(String productId);
    //下架
    void offSale(String productId);
}
