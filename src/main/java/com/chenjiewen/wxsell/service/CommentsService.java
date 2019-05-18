package com.chenjiewen.wxsell.service;

import com.chenjiewen.wxsell.model.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsService {
    void add(Comments comments);
    List<Comments> getBySellerId( String sellerId);
    void updateCommentStatus( String orderId);
    void updateStar(String orderId);

}
