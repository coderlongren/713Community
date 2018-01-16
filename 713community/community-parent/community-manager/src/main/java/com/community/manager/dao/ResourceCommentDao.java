package com.community.manager.dao;

import com.community.manager.entity.ResourceComment;
import com.community.manager.entity.ResourceHistoryComment;

import java.util.List;

/**
 * @Author:王喜
 * @Description:资源评论Dao
 * @Date:2017/11/16 19:36.
 */
public interface ResourceCommentDao extends BaseDao<ResourceComment> {

    List<ResourceComment> listAllWithSearch(String searchVal);
}
