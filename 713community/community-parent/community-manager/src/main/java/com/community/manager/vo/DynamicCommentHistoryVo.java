package com.community.manager.vo;

import com.community.manager.entity.DynamicCommentHistory;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:20.
 */
public class DynamicCommentHistoryVo {

    private DynamicCommentHistory dynamicCommentHistory;
    private CommentUserVo commentUserVo;

    public DynamicCommentHistoryVo() {

    }

    @Override
    public String toString() {
        return "DynamicCommentHistoryVo{" +
                "dynamicCommentHistory=" + dynamicCommentHistory +
                ", commentUserVo=" + commentUserVo +
                '}';
    }

    public DynamicCommentHistory getDynamicCommentHistory() {
        return dynamicCommentHistory;
    }

    public void setDynamicCommentHistory(DynamicCommentHistory dynamicCommentHistory) {
        this.dynamicCommentHistory = dynamicCommentHistory;
    }

    public CommentUserVo getCommentUserVo() {
        return commentUserVo;
    }

    public void setCommentUserVo(CommentUserVo commentUserVo) {
        this.commentUserVo = commentUserVo;
    }
}
