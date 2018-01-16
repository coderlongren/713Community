package com.community.manager.dao;

import com.community.manager.entity.ResourceHistoryComment;

import java.util.List;

/**
 * @Author:王喜
 * @Description:
 * @Date:2017/11/16 19:36.
 */
public interface ResourceHistoryCommentDao extends BaseDao<ResourceHistoryComment> {

    List<ResourceHistoryComment> listAllWithSearch(String searchVal);
}
