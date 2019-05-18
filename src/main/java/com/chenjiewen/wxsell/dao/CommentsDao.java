package com.chenjiewen.wxsell.dao;

import com.chenjiewen.wxsell.model.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsDao {
    void add(Comments comments);
    List<Comments> getBySellerId(@Param("sellerId") String sellerId);
    void updateCommentStatus(@Param("orderId") String orderId);
    void updateStar(@Param("orderId") String orderId);
}
