package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.dao.ProductCategoryDao;
import com.chenjiewen.wxsell.model.ProductCategory;
import com.chenjiewen.wxsell.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
//类目

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
    private ProductCategoryDao productCategoryDao;

    //增加
    public void addProductCategory(ProductCategory productCategory) {
        productCategoryDao.addProductCategory(productCategory);
    }

    //通过type int集合查询
    public List<ProductCategory> selectByCategoryTypeIn(String sellerId,List<Integer> categoryType) {
        return productCategoryDao.selectByCategoryTypeIn(sellerId,categoryType);
    }

    //查询全部
    public List<ProductCategory> selectAllProductCategory( String sellerId) {
        return productCategoryDao.selectAllProductCategory(sellerId);
    }

   //通过id查询
    public ProductCategory selectByCategoryId(String id) {
        return  productCategoryDao.selectByCategoryId(id);
    }

    //更新 通过对象
    public void updateProductCategory(ProductCategory productCategory) {
        productCategoryDao.updateProductCategory(productCategory);
    }

    //通过categoryId删除
    public void deleteByCategoryId(int categoryId) {
        productCategoryDao.deleteByCategoryId(categoryId);
    }
}
