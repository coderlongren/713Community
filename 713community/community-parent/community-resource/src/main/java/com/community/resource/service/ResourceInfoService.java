package com.community.resource.service;

import com.community.resource.vo.ResourceInfoLZVo;

import java.io.IOException;

/**
 * @Author:王喜
 * @Description :资源详情Service层
 * @Date: 2017/12/25 0025 9:23
 */
public interface ResourceInfoService {
    /**
     * 根据资源id查询资源详情
     *
     * @param resourceId
     * @return
     */
    ResourceInfoLZVo viewLZAndTopInfoByResourceId(Long resourceId) throws IOException;
}
