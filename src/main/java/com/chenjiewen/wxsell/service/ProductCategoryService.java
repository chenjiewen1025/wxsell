package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.model.ProductCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductCategoryService {
    void addProductCategory(ProductCategory productCategory);
    List<ProductCategory> selectByCategoryTypeIn(List<Integer> categoryType);
    List<ProductCategory> selectAllProductCategory();
    ProductCategory selectByCategoryId(int id);
    //更新
    void updateProductCategory(ProductCategory productCategory);
    //通过id删除
    void deleteByCategoryId(int categoryId);
}
