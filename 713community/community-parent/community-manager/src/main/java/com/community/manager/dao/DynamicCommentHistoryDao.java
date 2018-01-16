package com.community.manager.dao;

import com.community.manager.entity.DynamicComment;
import com.community.manager.entity.DynamicCommentHistory;

import java.util.List;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:36.
 */
public interface DynamicCommentHistoryDao extends BaseDao<DynamicCommentHistory> {

    List<DynamicCommentHistory> listAllWithSearch(String searchVal);
}
