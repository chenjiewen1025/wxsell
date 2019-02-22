package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.dao.OrderDetailDao;
import com.chenjiewen.wxsell.dao.OrderMasterDao;
import com.chenjiewen.wxsell.dto.CarDTO;
import com.chenjiewen.wxsell.enums.OrderStatusEnum;
import com.chenjiewen.wxsell.enums.PayStatusEnum;
import com.chenjiewen.wxsell.enums.ResultEnum;
import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.model.OrderDetail;
import com.chenjiewen.wxsell.model.OrderMaster;
import com.chenjiewen.wxsell.model.ProductInfo;
import com.chenjiewen.wxsell.service.OrderService;
import com.chenjiewen.wxsell.service.PayService;
import com.chenjiewen.wxsell.service.ProductInfoService;
import com.chenjiewen.wxsell.service.WebSocket;
import com.chenjiewen.wxsell.utils.KeyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMasterDao orderMasterDao;
    @Resource
    private ProductInfoService productInfoService;
    @Resource
    private OrderDetailDao orderDetailDao;

    @Resource
    private PayService payService;

    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional
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
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //3.
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);

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

        try {
            webSocket.sendMessage("有新订单");
        }
        catch (Exception e)
        {
            e.getMessage();
        }

        return orderMaster;
    }

    @Override
    public OrderMaster selectByOrderId(String orderId) {
        return orderMasterDao.selectByOrderId(orderId);
    }



    public PageInfo<OrderMaster> selectAll(int page,int size) {
        PageHelper.startPage(page, size);
        PageInfo<OrderMaster> pageInfo = new PageInfo<>(orderMasterDao.selectAll());
        return pageInfo;
    }

    @Override
    public List<OrderMaster> selectByBuyerOpenid(String buyerOpenid) {
        return orderMasterDao.selectByBuyerOpenid(buyerOpenid);
    }

    @Override
    @Transactional
    public OrderMaster cancel(OrderMaster orderMaster) {
        //1.判断订单状态
        //2.修改订单状态
        //3.返回库存
        //4.退款
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
        {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
       orderMasterDao.updateMaster(orderMaster);

       if (CollectionUtils.isEmpty(orderMaster.getOrderDetailList()))
       {
           throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
       }
       List<CarDTO> carDTOList = orderMaster.getOrderDetailList().stream()
               .map(e ->new CarDTO(e.getProductId(),e.getProductQuantity()))
               .collect(Collectors.toList());
       productInfoService.increaseStock(carDTOList);


       if (orderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode()))
       {
           //todo
           payService.create(orderMaster,"订单已取消掉！");
       }
        return orderMaster;

    }

    @Override
    @Transactional
    public OrderMaster finish(OrderMaster orderMaster) {

        /*
        * 1.判断订单状态
        * 2.修改状态
        *
        * */
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
        {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterDao.updateMaster(orderMaster);
        payService.create(orderMaster,"订单已完成！");

        return orderMaster;
    }

    @Override
    @Transactional
    public OrderMaster paid(OrderMaster orderMaster) {

        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
        {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        if (!orderMaster.getPayStatus().equals(PayStatusEnum.WAIT.getCode()))
        {
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());

        orderMasterDao.updateMaster(orderMaster);
        payService.create(orderMaster,"支付成功！请留意后续！");
        return orderMaster;
    }
}
