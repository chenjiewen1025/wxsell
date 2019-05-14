package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.VO.*;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface DataShowService {
    StatisticsBase getStatisticsBase(String dateSize,String sellerId);
    List<ProductTop> getTop(String dateSize, String sellerId);
    AmountTop getTopAmount(String dateSize,String sellerId);
    DayData getDayData(String dateSize,String sellerId);
    SevenDay getSevenDay(String sellerId);
}
