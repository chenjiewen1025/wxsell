package com.chenjiewen.wxsell.service.impl;

import com.chenjiewen.wxsell.dao.CommentsDao;
import com.chenjiewen.wxsell.model.Comments;
import com.chenjiewen.wxsell.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentsDao commentsDao;

    @Override
    public void add(Comments comments) {
        commentsDao.add(comments);
    }

    @Override
    public List<Comments> getBySellerId(String sellerId) {
        return commentsDao.getBySellerId(sellerId);
    }

    @Override
    public void updateCommentStatus(String orderId) {
        commentsDao.updateCommentStatus(orderId);
    }

    @Override
    public void updateStar(String orderId) {
        commentsDao.updateStar(orderId);
    }
}
