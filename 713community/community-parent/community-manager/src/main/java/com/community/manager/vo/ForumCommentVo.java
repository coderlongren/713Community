package com.community.manager.vo;

import com.community.manager.entity.DynamicComment;
import com.community.manager.entity.ForumComment;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:20.
 */
public class ForumCommentVo {

    private ForumComment forumComment;
    private CommentUserVo commentUserVo;

    public ForumCommentVo() {

    }

    @Override
    public String toString() {
        return "ForumCommentVo{" +
                "forumComment=" + forumComment +
                ", commentUserVo=" + commentUserVo +
                '}';
    }

    public ForumComment getForumComment() {
        return forumComment;
    }

    public void setForumComment(ForumComment forumComment) {
        this.forumComment = forumComment;
    }

    public CommentUserVo getCommentUserVo() {
        return commentUserVo;
    }

    public void setCommentUserVo(CommentUserVo commentUserVo) {
        this.commentUserVo = commentUserVo;
    }
}
