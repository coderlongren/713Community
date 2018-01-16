package com.community.resource.dao;

import com.community.resource.entity.ResourceInfo;

/**
 * @Author:王喜
 * @Description :资源详情dao 而ResourceInfoDao是资源dao
 * @Date: 2018/1/12 0012 15:28
 */
public interface ResourceInformationDao {

    /**
     * 插入资源详情
     *
     * @param resourceInfo
     * @return
     */
    Integer insertResourceInfo(ResourceInfo resourceInfo);
}
