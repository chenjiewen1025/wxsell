package com.chenjiewen.wxsell.controller;

import com.chenjiewen.wxsell.model.Comments;
import com.chenjiewen.wxsell.service.CommentsService;
import com.chenjiewen.wxsell.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comments")
public class CommentsController {


    @Autowired
    private CommentsService commentsService;



    @RequestMapping("/save")
    @ResponseBody
    public String save(Comments comments, Model model){

        comments.setId(KeyUtil.genUniqueKey());
        commentsService.add(comments);
        commentsService.updateCommentStatus(comments.getOrderId());
        commentsService.updateStar(comments.getOrderId());
        return "1";
    }


}
