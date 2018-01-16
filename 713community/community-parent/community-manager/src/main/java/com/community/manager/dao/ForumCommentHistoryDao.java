package com.community.manager.dao;

import com.community.manager.entity.DynamicCommentHistory;
import com.community.manager.entity.ForumCommentHistory;

import java.util.List;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:36.
 */
public interface ForumCommentHistoryDao extends BaseDao<ForumCommentHistory> {

    List<ForumCommentHistory> listAllWithSearch(String searchVal);
}
