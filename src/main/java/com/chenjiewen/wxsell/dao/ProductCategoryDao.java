package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    //增加
    void addProductCategory(ProductCategory productCategory);
    //查询全部
    List<ProductCategory> selectAllProductCategory(@Param("sellerId") String sellerId);
    //通过type查
    List<ProductCategory> selectByCategoryTypeIn(@Param("categoryType") List<Integer> categoryType);
    //通过id查
    ProductCategory selectByCategoryId(@Param("categoryId") Integer id);
    //更新
    void updateProductCategory(ProductCategory productCategory);
    //通过id删除
    void deleteByCategoryId(@Param("categoryId") int categoryId);

}
