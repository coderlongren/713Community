package com.community.manager.service;

import com.community.manager.entity.ResourceHistoryComment;
import com.community.manager.vo.PageResultVo;

/**
 * @Author:王喜
 * @Description:资源历史评论接口
 * @Date:2017/11/16 19:29.
 */
public interface ResourceHistoryCommentService {

    /**
     * 分页查询资源历史评论
     *
     * @param page
     * @return
     */
    PageResultVo listAllResourceHistoryCommentByPage(Integer page);

    /**
     * 根据id查询资源历史评论
     *
     * @param id
     * @return
     */
    ResourceHistoryComment getResourceHistoryCommentById(Long id);

    /**
     * 根据id增加资源历史评论
     *
     * @param resourceHistoryComment
     * @return
     */
    Boolean insertResourceHistoryComment(ResourceHistoryComment resourceHistoryComment);

    /**
     * 根据id删除资源历史评论
     *
     * @param resourceHistoryCommentId
     * @return
     */
    Boolean removeResourceHistoryCommentById(Long resourceHistoryCommentId);

    /**
     * 模糊搜索资源历史评论
     *
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listResourceHistoryCommentWithSearch(String searchVal, Integer page, Integer rows);
}
