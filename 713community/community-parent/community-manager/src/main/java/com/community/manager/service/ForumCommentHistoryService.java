package com.community.manager.service;

import com.community.manager.entity.ForumCommentHistory;
import com.community.manager.vo.PageResultVo;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:29.
 */
public interface ForumCommentHistoryService {

    /**
     * 分页查询论坛评论历史
     * @param page
     * @return
     */
    PageResultVo listAllForumCommentByPage(Integer page);

    /**
     * 根据id查询论坛评论历史
     * @param id
     * @return
     */
    ForumCommentHistory getForumCommentById(Long id);

    /**
     * 插入论坛评论历史
     * @param forumCommentHistory
     * @return
     */
    Boolean insertForumComment(ForumCommentHistory forumCommentHistory);

    /**
     * 删除论坛评论历史
     * @param ForumCommentId
     * @return
     */
    Boolean removeForumCommentById(Long ForumCommentId);

    /**
     * 搜索论坛评论历史
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listForumCommentWithSearch(String searchVal, Integer page, Integer rows);
}
