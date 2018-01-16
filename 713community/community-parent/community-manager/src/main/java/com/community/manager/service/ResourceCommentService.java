package com.community.manager.service;

import com.community.manager.entity.ResourceComment;
import com.community.manager.entity.ResourceHistoryComment;
import com.community.manager.vo.PageResultVo;

/**
 * @Author:王喜
 * @Description:资源评论接口
 * @Date:2017/11/16 19:29.
 */
public interface ResourceCommentService {

    /**
     * 分页查询资源评论
     *
     * @param page
     * @return
     */
    PageResultVo listAllResourceCommentByPage(Integer page);

    /**
     * 根据id查询资源评论
     *
     * @param id
     * @return
     */
    ResourceComment getResourceCommentById(Long id);

    /**
     * 根据id增加资源评论
     *
     * @param resourceComment
     * @return
     */
    Boolean insertResourceComment(ResourceComment resourceComment);

    /**
     * 根据id删除资源评论
     *
     * @param resourceCommentId
     * @return
     */
    Boolean removeResourceCommentById(Long resourceCommentId);

    /**
     * 模糊搜索资源评论
     *
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listResourceCommentWithSearch(String searchVal, Integer page, Integer rows);
}
