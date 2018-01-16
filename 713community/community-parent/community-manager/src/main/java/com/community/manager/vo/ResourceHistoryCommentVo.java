package com.community.manager.vo;

import com.community.manager.entity.ResourceHistoryComment;

/**
 * @Author:王喜
 * @Description: 资源历史评论Vo
 * @Date:2017/11/16 19:20.
 */
public class ResourceHistoryCommentVo {

    private ResourceHistoryComment resourceHistoryComment;

    private ResourceCommentUserVo resourceCommentUserVo;

    public ResourceHistoryCommentVo() {

    }

    public ResourceHistoryComment getResourceHistoryComment() {
        return resourceHistoryComment;
    }

    public void setResourceHistoryComment(ResourceHistoryComment resourceHistoryComment) {
        this.resourceHistoryComment = resourceHistoryComment;
    }

    public ResourceHistoryCommentVo(ResourceHistoryComment resourceHistoryComment, ResourceCommentUserVo resourceCommentUserVo) {
        this.resourceHistoryComment = resourceHistoryComment;
        this.resourceCommentUserVo = resourceCommentUserVo;
    }

    public ResourceCommentUserVo getResourceCommentUserVo() {
        return resourceCommentUserVo;
    }

    public void setResourceCommentUserVo(ResourceCommentUserVo resourceCommentUserVo) {
        this.resourceCommentUserVo = resourceCommentUserVo;
    }
}