package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.VO.*;
import com.chenjiewen.wxsell.exception.SellException;
import com.chenjiewen.wxsell.model.SellerInfo;
import com.chenjiewen.wxsell.service.DataShowService;
import com.chenjiewen.wxsell.service.SellerService;
import com.google.gson.JsonArray;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seller/datashow")
public class DataShowController {

    @Autowired
    private DataShowService showService;

    @RequestMapping("/list")
    public String list(Model model, HttpSession session,
                       @RequestParam(value = "date" ,required = false) String date){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(date==null)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());//设置起时间
            cal.add(Calendar.DATE, 1);//增加一天
            date  = sdf.format(new Date())+" - "+sdf.format(cal.getTime());
        }

        model.addAttribute("date",date);
        SellerInfo seller = (SellerInfo) session.getAttribute("seller");
        seller.setPassword(null);
        model.addAttribute("seller",seller);
        StatisticsBase statisticsBase = showService.getStatisticsBase(date,seller.getSellerId());
        model.addAttribute("statisticsBase",statisticsBase);
        List<ProductTop> productTopList = showService.getTop(date,seller.getSellerId());
        model.addAttribute("productTopList",productTopList);
        AmountTop amountTop = showService.getTopAmount(date,seller.getSellerId());
        model.addAttribute("amountTop",amountTop);
        DayData dayData = showService.getDayData(date,seller.getSellerId());
        SevenDay sevenDay = showService.getSevenDay(seller.getSellerId());
        model.addAttribute("sevenDay",sevenDay);
        model.addAttribute("daydata",dayData);
        return "datashow/show";
    }


}
