package com.community.manager.service;

import com.community.manager.entity.DynamicComment;
import com.community.manager.vo.PageResultVo;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:29.
 */
public interface DynamicCommentService {

    /**
     * 分页查询动态评论
     * @param page
     * @return
     */
    PageResultVo listAllDynamicCommentByPage(Integer page);

    /**
     * 根据id查询动态评论
     * @param id
     * @return
     */
    DynamicComment getDynamicCommentById(Long id);

    /**
     * 插入动态评论
     * @param dynamicComment
     * @return
     */
    Boolean insertDynamicComment(DynamicComment dynamicComment);

    /**
     * 根据id删除动态评论
     * @param dynamicCommentId
     * @return
     */
    Boolean removeDynamicCommentById(Long dynamicCommentId);

    /**
     * 搜索动态评论
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listDynamicCommentWithSearch(String searchVal,Integer page,Integer rows);
}
