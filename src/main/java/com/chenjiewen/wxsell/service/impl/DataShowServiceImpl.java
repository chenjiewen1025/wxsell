package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.VO.*;
import com.chenjiewen.wxsell.dao.DataShowDao;
import com.chenjiewen.wxsell.service.DataShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DataShowServiceImpl implements DataShowService {

    @Resource
    private DataShowDao showDao;


    @Override
    public StatisticsBase getStatisticsBase(String dateSize,String sellerId) {

        Date star=null,end=null;
        if (dateSize!=null) {
            String[] startAndEnd = dateSize.split(" - ");   //分割开始，结束时间   中间有空格，勿删除
            String start1 = startAndEnd[0];
            String end1 = startAndEnd[1];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//定义日期格式

            try {
                star  = format.parse(start1);//将字符串转换为日期
                end  = format.parse(end1);//将字符串转换为日期

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        return showDao.getStatisticsBase(star,end,sellerId);
    }


    @Override
    public List<ProductTop> getTop(String dateSize, String sellerId) {

        Date star=null,end=null;
        if (dateSize!=null) {
            String[] startAndEnd = dateSize.split(" - ");   //分割开始，结束时间   中间有空格，勿删除
            String start1 = startAndEnd[0];
            String end1 = startAndEnd[1];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//定义日期格式

            try {
                star  = format.parse(start1);//将字符串转换为日期
                end  = format.parse(end1);//将字符串转换为日期

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        return showDao.getTop(star,end,sellerId);
    }

    @Override
    public AmountTop getTopAmount(String dateSize, String sellerId) {
        Date star=null,end=null;
        if (dateSize!=null) {
            String[] startAndEnd = dateSize.split(" - ");   //分割开始，结束时间   中间有空格，勿删除
            String start1 = startAndEnd[0];
            String end1 = startAndEnd[1];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//定义日期格式

            try {
                star  = format.parse(start1);//将字符串转换为日期
                end  = format.parse(end1);//将字符串转换为日期

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        return showDao.getTopAmount(star,end,sellerId);
    }

    @Override
    public DayData getDayData(String dateSize, String sellerId) {
        Date star=null,end=null;
        if (dateSize!=null) {
            String[] startAndEnd = dateSize.split(" - ");   //分割开始，结束时间   中间有空格，勿删除
            String start1 = startAndEnd[0];
            String end1 = startAndEnd[1];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//定义日期格式

            try {
                star  = format.parse(start1);//将字符串转换为日期
                end  = format.parse(end1);//将字符串转换为日期

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        return showDao.getDayData(star,end,sellerId);
    }

    @Override
    public SevenDay getSevenDay(String sellerId) {
        return showDao.getSevenDay(sellerId);
    }
}
