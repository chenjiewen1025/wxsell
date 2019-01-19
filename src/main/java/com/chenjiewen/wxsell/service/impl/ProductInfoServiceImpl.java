package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.dao.ProductInfoDao;
import com.chenjiewen.wxsell.enums.ProductStatusEnum;
import com.chenjiewen.wxsell.model.ProductInfo;
import com.chenjiewen.wxsell.service.ProductCategoryService;
import com.chenjiewen.wxsell.service.ProductInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Resource
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo selectByProductId(String productId) {
        return productInfoDao.selectByProductId(productId);
    }

    @Override
    public List<ProductInfo> selectAll() {
        return productInfoDao.selectAll();
    }

    @Override
    public List<ProductInfo> selectUpAll() {
        return productInfoDao.selectByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public void deleteById(String id) {
        productInfoDao.deleteById(id);
    }

    @Override
    public void addProductInfo(ProductInfo productInfo) {
        productInfoDao.addProductInfo(productInfo);
    }
}
