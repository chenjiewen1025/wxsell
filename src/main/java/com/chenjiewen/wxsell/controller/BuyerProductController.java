package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.VO.ProductInfoVO;
import com.chenjiewen.wxsell.VO.ProductVO;
import com.chenjiewen.wxsell.VO.ResultVO;
import com.chenjiewen.wxsell.dao.ProductInfoDao;
import com.chenjiewen.wxsell.enums.ProductStatusEnum;
import com.chenjiewen.wxsell.model.Comments;
import com.chenjiewen.wxsell.model.ProductCategory;
import com.chenjiewen.wxsell.model.ProductInfo;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.CommentsService;
import com.chenjiewen.wxsell.service.ProductCategoryService;
import com.chenjiewen.wxsell.service.ProductInfoService;
import com.chenjiewen.wxsell.service.SellerService;
import com.chenjiewen.wxsell.utils.ResultVOUtil;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Resource
    private ProductInfoService productInfoService;
    @Resource
    private ProductCategoryService productCategoryService;

    @Resource
    private ProductInfoDao productInfoDao;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private SellerService sellerService;

    @RequestMapping("/list")
    public String list(@RequestParam("sellerId") String sellerId,
                       Model model){

        /*

     1.查询所有上架商品
     2.查询类目
     3.数据拼装
         */
        //1
        List<ProductInfo> productInfoList =
                productInfoDao.selectByProductStatus(ProductStatusEnum.UP.getCode(),sellerId);

        //2
        List<Integer> categoryTypeList = new ArrayList<>();

        for (ProductInfo item:productInfoList)
        {
            categoryTypeList.add(item.getCategoryType());
        }
        List<ProductCategory> productCategoryList =
                productCategoryService.selectByCategoryTypeIn(sellerId,categoryTypeList);

        //3
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList)
        {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:productInfoList)
            {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType()))
                {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        SellerInfo seller = sellerService.selectById(sellerId);
        model.addAttribute("seller",seller);
       model.addAttribute("productVOList",productVOList);
        List<Comments> comments = commentsService.getBySellerId(sellerId);
        model.addAttribute("comments",comments);
        return "buyer/shopDetail";
    }



    @RequestMapping("/test")
    public String test(){
        return "buyer/shopDetail";
    }

}
