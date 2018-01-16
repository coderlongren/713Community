package com.community.resource.dao;

import com.community.resource.entity.ResourceType;

import java.util.List;

/**
 * @Author:王喜
 * @Description :资源共享首页dao
 * @Date: 2017/12/18 0018 23:35
 */
public interface IndexDao {

    /**
     * 获取资源类别信息
     *
      * @return
     */
   List<ResourceType> listResourceTypeInfo();

    /**
     * 根据资源id查询资源类别
     * @param resourceTypeId
     * @return
     */
    ResourceType getById(Long resourceTypeId);
}
