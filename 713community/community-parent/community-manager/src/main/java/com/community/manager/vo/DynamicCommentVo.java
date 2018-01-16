package com.community.manager.vo;

import com.community.manager.entity.DynamicComment;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:20.
 */
public class DynamicCommentVo {

    private DynamicComment dynamicComment;
    private CommentUserVo commentUserVo;

    public DynamicCommentVo() {

    }

    @Override
    public String toString() {
        return "DynamicCommentVo{" +
                "dynamicComment=" + dynamicComment +
                ", commentUserVo=" + commentUserVo +
                '}';
    }

    public DynamicComment getDynamicComment() {
        return dynamicComment;
    }

    public void setDynamicComment(DynamicComment dynamicComment) {
        this.dynamicComment = dynamicComment;
    }

    public CommentUserVo getCommentUserVo() {
        return commentUserVo;
    }

    public void setCommentUserVo(CommentUserVo commentUserVo) {
        this.commentUserVo = commentUserVo;
    }

    public DynamicCommentVo(DynamicComment dynamicComment, CommentUserVo commentUserVo) {
        this.dynamicComment = dynamicComment;
        this.commentUserVo = commentUserVo;
    }
}
