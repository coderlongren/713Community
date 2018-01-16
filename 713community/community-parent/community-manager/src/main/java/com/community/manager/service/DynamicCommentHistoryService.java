package com.community.manager.service;

import com.community.manager.entity.DynamicComment;
import com.community.manager.entity.DynamicCommentHistory;
import com.community.manager.vo.PageResultVo;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:29.
 */
public interface DynamicCommentHistoryService {

    /**
     * 分页查询动态评论历史
     * @param page
     * @return
     */
    PageResultVo listAllDynamicCommentByPage(Integer page);

    /**
     * 根据id查询动态评论历史
     * @param id
     * @return
     */
    DynamicCommentHistory getDynamicCommentById(Long id);

    /**
     * 插入动态评论历史
     * @param dynamicComment
     * @return
     */
    Boolean insertDynamicComment(DynamicCommentHistory dynamicComment);

    /**
     * 删除动态评论历史
     * @param dynamicCommentId
     * @return
     */
    Boolean removeDynamicCommentById(Long dynamicCommentId);

    /**
     * 搜索动态评论历史
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listDynamicCommentWithSearch(String searchVal, Integer page, Integer rows);
}
