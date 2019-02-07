package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.dao.ProductInfoDao;
import com.chenjiewen.wxsell.dto.CarDTO;
import com.chenjiewen.wxsell.enums.ProductStatusEnum;
import com.chenjiewen.wxsell.enums.ResultEnum;
import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.model.ProductInfo;
import com.chenjiewen.wxsell.service.ProductCategoryService;
import com.chenjiewen.wxsell.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PageInfo<ProductInfo> selectAll(int page,int size) {
        PageHelper.startPage(page, size);
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(productInfoDao.selectAll());
        return pageInfo;
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

    @Override
    public void increaseStock(List<CarDTO> carDTOList) {

        for (CarDTO carDTO:carDTOList)
        {
            ProductInfo productInfo = productInfoDao.selectByProductId(carDTO.getProductId());
            if (productInfo==null)
            {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + carDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoDao.updateProductInfo(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CarDTO> carDTOList) {
        for (CarDTO carDTO:carDTOList)
        {
            ProductInfo productInfo = productInfoDao.selectByProductId(carDTO.getProductId());
            if (productInfo==null)
            {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock()- carDTO.getProductQuantity();
            if (result<0)
            {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoDao.updateProductInfo(productInfo);
        }
    }

    @Override
    public void onSale(String productId) {
        ProductInfo productInfo = productInfoDao.selectByProductId(productId);
        if (productInfo==null)
        {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum()==ProductStatusEnum.UP)
        {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfoDao.updateProductInfo(productInfo);

    }

    @Override
    public void offSale(String productId) {
        ProductInfo productInfo = productInfoDao.selectByProductId(productId);
        if (productInfo==null)
        {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum()==ProductStatusEnum.DOWN)
        {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfoDao.updateProductInfo(productInfo);
    }
}
