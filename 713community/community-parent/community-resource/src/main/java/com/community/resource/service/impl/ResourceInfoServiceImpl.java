package com.community.resource.service.impl;

import com.community.resource.dao.ResourceCommentDao;
import com.community.resource.dao.ResourceInfoDao;
import com.community.resource.entity.Resource;
import com.community.resource.entity.User;
import com.community.resource.service.ResourceInfoService;
import com.community.resource.vo.ResourceInfoLZVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author:王喜
 * @Description :资源详情Service层实现类
 * @Date: 2017/12/25 0025 9:23
 */
@Service
public class ResourceInfoServiceImpl implements ResourceInfoService {

    @Autowired
    private ResourceInfoDao resourceInfoDao;

    @Autowired
    private ResourceCommentDao resourceCommentDao;

    /**
     * 根据资源id查询资源详情
     *
     * @param resourceId
     * @return
     */
    @Override
    public ResourceInfoLZVo viewLZAndTopInfoByResourceId(Long resourceId) throws IOException {

        //每点进一个资源链接，查看其详细情况时，浏览量就加一
        updataResourceBrowseNum(resourceId);


        //获取资源信息
        Resource resource = resourceInfoDao.getResourceInfoById(resourceId);

        //获取资源的回复数
        Integer resourceComment = resourceCommentDao.countResourceComment(resourceId);
        resource.setResourceComment(resourceComment);
        //获取该用户（楼主）的发布资源总数
        Integer resourceReleaseCount = resourceInfoDao.getResourceReleaseCount(resource.getUserId());
        User user = resource.getUser();
        user.setResourceReleaseCount(resourceReleaseCount);
        resource.setUser(user);
        ResourceInfoLZVo resourceInfoLZVo = new ResourceInfoLZVo(resource);
        return resourceInfoLZVo;
    }


    private Integer updataResourceBrowseNum(Long resourceId) {
        //先得到该资源的浏览量
        Integer browseNum = resourceInfoDao.getResourceBrowseNumById(resourceId);
        browseNum = browseNum + 1;
        Integer result = resourceInfoDao.updateBrowseNumByClickResourceLink(resourceId,browseNum);

        return  result;
    }
}
