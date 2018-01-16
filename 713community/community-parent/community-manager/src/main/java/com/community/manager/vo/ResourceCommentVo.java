package com.community.manager.vo;

import com.community.manager.entity.ResourceComment;

/**
 * @Author:王喜
 * @Description: 资源评论Vo
 * @Date:2017/11/16 19:20.
 */
public class ResourceCommentVo {

    private ResourceComment resourceComment;

    private ResourceCommentUserVo resourceCommentUserVo;

    public ResourceCommentVo() {

    }

    public ResourceCommentVo(ResourceComment resourceComment, ResourceCommentUserVo resourceCommentUserVo) {
        this.resourceComment = resourceComment;
        this.resourceCommentUserVo = resourceCommentUserVo;
    }

    public ResourceComment getResourceComment() {
        return resourceComment;
    }

    public void setResourceComment(ResourceComment resourceComment) {
        this.resourceComment = resourceComment;
    }

    public ResourceCommentUserVo getResourceCommentUserVo() {
        return resourceCommentUserVo;
    }

    public void setResourceCommentUserVo(ResourceCommentUserVo resourceCommentUserVo) {
        this.resourceCommentUserVo = resourceCommentUserVo;
    }
}