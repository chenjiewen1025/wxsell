package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.VO.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface DataShowDao {

    StatisticsBase getStatisticsBase(@Param("star") Date star, @Param("end") Date end,
                                     @Param("sellerId") String sellerId);

    List<ProductTop> getTop(@Param("star") Date star, @Param("end") Date end,
                            @Param("sellerId") String sellerId);

    AmountTop getTopAmount(@Param("star") Date star, @Param("end") Date end,
                           @Param("sellerId") String sellerId);

    DayData getDayData(@Param("star") Date star, @Param("end") Date end,
                       @Param("sellerId") String sellerId);

    SevenDay getSevenDay(@Param("sellerId") String sellerId);


}
