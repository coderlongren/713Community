package com.community.manager.service;

import com.community.manager.entity.ResourceInfo;

/**
 * @Author:王喜
 * @Description : 资源详情Serivce
 * @Date: 2017/11/8 0008 11:25
 */
public interface ResourceInfoService {
    /**
     * 根据资源id查询资源内容
     *
     * @param contentId
     * @return
     */
    ResourceInfo getContentById(Long contentId);
}
