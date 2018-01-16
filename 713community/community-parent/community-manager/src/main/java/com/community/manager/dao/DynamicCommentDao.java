package com.community.manager.dao;

import com.community.manager.entity.DynamicComment;

import java.util.List;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:36.
 */
public interface DynamicCommentDao extends BaseDao<DynamicComment> {

    List<DynamicComment> listAllWithSearch(String searchVal);
}
