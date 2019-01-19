package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Resource
    private ProductInfoDao productInfoDao;
    @Test
    public void selectByProductStatus() {
        List<ProductInfo> productInfo = productInfoDao.selectByProductStatus(2);
        System.out.println("d");
    }
    @Test
    public void addProductInfoTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(5.5));
        productInfo.setProductStock(70);
        productInfo.setProductDescription("很好喝");
        productInfo.setProductIcon("https:ddfds.com");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(5);
        productInfoDao.addProductInfo(productInfo);
    }
    @Test
    public void deleteById(){
        productInfoDao.deleteById("1");
    }
    @Test
    public void updateById(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setProductName("皮蛋");
        productInfoDao.updateProductInfo(productInfo);
    }
}