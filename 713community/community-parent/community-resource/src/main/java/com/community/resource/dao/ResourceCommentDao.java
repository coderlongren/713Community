package com.community.resource.dao;

import com.community.resource.entity.ResourceComment;

import java.util.List;

/**
 * @Author:王喜
 * @Description :资源评论Dao
 * @Date: 2017/12/20 0020 18:38
 */
public interface ResourceCommentDao {

    /**
     * 根据资源id查询资源的评论数量
     *
     * @param resourceId
     * @return
     */
    Integer countResourceComment(Long resourceId);

    /**
     * 根据资源id查询资源所有评论
     *
     * @param resourceId
     * @return
     */
    List<ResourceComment> listAllCommentByResourceId(Long resourceId);


    /**
     * 插入一条对楼主的评论
     *
     * @param resourceComment
     * @return
     */
    Integer insertComment(ResourceComment resourceComment);

    /**
     * 查出每个评论的其他评论信息
     *
     * @param commentIds
     * @return
     */
    List<ResourceComment> listByCommentIds(List<Long> commentIds);

}
