package com.chenjiewen.wxsell.controller;


import com.chenjiewen.wxsell.VO.BuyerOrderVo;
import com.chenjiewen.wxsell.VO.ShopList;
import com.chenjiewen.wxsell.model.SellerCategory;
import com.chenjiewen.wxsell.service.OrderService;
import com.chenjiewen.wxsell.service.SellerCategoryService;
import com.chenjiewen.wxsell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/buyer")
public class BuyerLoginController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SellerCategoryService sellerCategoryService;

    @RequestMapping("index")
        public String index(HttpSession session,
                            Model model){
        model.addAttribute("openId",session.getAttribute("openId"));
        model.addAttribute("nickname",session.getAttribute("nickname"));
        model.addAttribute("sexDesc",session.getAttribute("sexDesc"));
        model.addAttribute("headingUrl",session.getAttribute("headingUrl"));
         List<ShopList> shopLists =  sellerService.getAll();
         model.addAttribute("shopList",shopLists);
         List<SellerCategory> categoryList = sellerCategoryService.selectAll();
         model.addAttribute("categoryList",categoryList);
        return "buyer/index";
        }


    @RequestMapping("indexByCategory")
    public String indexByCategory(@RequestParam("category") String category,
                        Model model){

        SellerCategory type = sellerCategoryService.selectByValue(category);
        List<ShopList> shopLists =  sellerService.getByCategory(category);
        model.addAttribute("shopList",shopLists);
        model.addAttribute("type",type.getName());

        return "buyer/index";
    }


    @RequestMapping("order")
    public String order(@RequestParam("openId") String openId,
                                  Model model){
    List<BuyerOrderVo> orderVoList = orderService.getByOpenId(openId);
    model.addAttribute("orderVoList",orderVoList);
      return "buyer/order";
    }

    @RequestMapping("/addcomment")
    public String addComment(String orderId, Model model){
        model.addAttribute("orderId",orderId);
        return "/buyer/addcomment";
    }


    @RequestMapping("myMess")
    public String myMess(Model model){

        return "buyer/myMess";
    }




}
