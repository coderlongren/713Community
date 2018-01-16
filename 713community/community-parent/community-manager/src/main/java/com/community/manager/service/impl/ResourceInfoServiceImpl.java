package com.community.manager.service.impl;

import com.community.manager.dao.ResourceInfoDao;
import com.community.manager.entity.ResourceInfo;
import com.community.manager.service.ResourceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:王喜
 * @Description :资源详情Serivce
 * @Date: 2017/11/8 0008 11:25
 */
@Service
public class ResourceInfoServiceImpl implements ResourceInfoService {

    @Autowired
    private ResourceInfoDao resourceInfoDao;
    /**
     * 根据资源id查询资源内容
     *
     * @param contentId
     * @return
     */
    @Override
    public ResourceInfo getContentById(Long contentId) {
        return resourceInfoDao.getById(contentId);
    }

}
