package com.community.manager.dao;

import com.community.manager.entity.ResourceType;

import java.util.List;

/**
 * @Author:王喜
 * @Description :
 * @Date: 2017/11/3 0003 13:18
 */
public interface ResourceTypeDao extends BaseDao<ResourceType>{
    /**
     * 根据查询条件模糊查询
     * @param searchParam
     * @return
     */
    List<ResourceType> listResourceTypeWithSearch(String searchParam);
}
