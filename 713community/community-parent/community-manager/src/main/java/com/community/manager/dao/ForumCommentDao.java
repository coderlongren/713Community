package com.community.manager.dao;

import com.community.manager.entity.ForumComment;

import java.util.List;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:36.
 */
public interface ForumCommentDao extends BaseDao<ForumComment> {

    List<ForumComment> listAllWithSearch(String searchVal);
}
