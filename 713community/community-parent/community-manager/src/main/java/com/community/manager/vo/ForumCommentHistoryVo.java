package com.community.manager.vo;

import com.community.manager.entity.DynamicCommentHistory;
import com.community.manager.entity.ForumCommentHistory;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:20.
 */
public class ForumCommentHistoryVo {

    private ForumCommentHistory  forumCommentHistory;
    private CommentUserVo commentUserVo;

    public ForumCommentHistoryVo() {

    }

    @Override
    public String toString() {
        return "ForumCommentHistoryVo{" +
                "forumCommentHistory=" + forumCommentHistory +
                ", commentUserVo=" + commentUserVo +
                '}';
    }

    public ForumCommentHistory getForumCommentHistory() {
        return forumCommentHistory;
    }

    public void setForumCommentHistory(ForumCommentHistory forumCommentHistory) {
        this.forumCommentHistory = forumCommentHistory;
    }

    public CommentUserVo getCommentUserVo() {
        return commentUserVo;
    }

    public void setCommentUserVo(CommentUserVo commentUserVo) {
        this.commentUserVo = commentUserVo;
    }
}
