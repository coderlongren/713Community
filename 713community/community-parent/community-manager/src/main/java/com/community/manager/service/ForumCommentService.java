package com.community.manager.service;

import com.community.manager.entity.ForumComment;
import com.community.manager.vo.PageResultVo;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:29.
 */
public interface ForumCommentService {
    /**
     * 分页查询论坛评论
     * @param page
     * @return
     */
    PageResultVo listAllForumCommentByPage(Integer page);

    /**
     * 根据id查询论坛评论
     * @param id
     * @return
     */
    ForumComment getForumCommentById(Long id);

    /**
     * 插入论坛评论
     * @param ForumComment
     * @return
     */
    Boolean insertForumComment(ForumComment ForumComment);

    /**
     * 删除论坛评论
     * @param ForumCommentId
     * @return
     */
    Boolean removeForumCommentById(Long ForumCommentId);

    /**
     * 搜索论坛评论
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listForumCommentWithSearch(String searchVal, Integer page, Integer rows);
}
