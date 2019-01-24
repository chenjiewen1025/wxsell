package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.dao.OrderDetailDao;
import com.chenjiewen.wxsell.dao.OrderMasterDao;
import com.chenjiewen.wxsell.dto.CarDTO;
import com.chenjiewen.wxsell.enums.ResultEnum;
import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.model.OrderDetail;
import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.model.ProductInfo;
import com.chenjiewen.wxsell.service.OrderService;
import com.chenjiewen.wxsell.service.ProductInfoService;
import com.chenjiewen.wxsell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMasterDao orderMasterDao;
    @Resource
    private ProductInfoService productInfoService;
    @Resource
    private OrderDetailDao orderDetailDao;
    @Override
    public OrderMaster create(OrderMaster orderMaster) {
        /*
        * 1.查询商品，数量，单价
        * 2.计算总价
        * 3.写入数据库
        * 4，扣库存
        * */
        String orderId = KeyUtil.genUniqueKey();

        List<CarDTO> carDTOList = new ArrayList<>();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail:orderMaster.getOrderDetailList())
        {
            ProductInfo productInfo = productInfoService.selectByProductId(orderDetail.getProductId());
            if (productInfo==null)
            {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.
            orderAmount = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //3.
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailDao.addOrderDetail(orderDetail);


            CarDTO carDTO = new CarDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            carDTOList.add(carDTO);
        }

        //3

        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterDao.addOrderMaster(orderMaster);
        //4

        productInfoService.decreaseStock(carDTOList);
        return orderMaster;
    }

    @Override
    public OrderMaster selectByOrderId(String orderId) {
        return orderMasterDao.selectByOrderId(orderId);
    }

    @Override
    public List<OrderMaster> selectAll() {
        return orderMasterDao.selectAll();
    }

    @Override
    public OrderMaster cancel(OrderMaster orderMaster) {
        return null;
    }

    @Override
    public OrderMaster finish(OrderMaster orderMaster) {
        return null;
    }

    @Override
    public OrderMaster paid(OrderMaster orderMaster) {
        return null;
    }
}
