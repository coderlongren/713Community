package com.community.resource.dao;

import com.community.resource.entity.Resource;
import com.community.resource.entity.ResourceInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:王喜
 * @Description :资源Dao层
 * @Date: 2017/12/25 0025 9:22
 */
public interface ResourceInfoDao {
    /**
     * 根据资源id查询资源信息
     *
     * @param resourceId
     * @return
     */
    Resource getResourceInfoById(Long resourceId);

    /**
     * 根据用户id查询该用户发布了资源总数
     *
     * @param userId
     * @return
     */
    Integer getResourceReleaseCount(Long userId);

    /**
     * 更新资源的浏览量
     *
     * @param resourceId
     * @param browseNum
     * @return
     */
    Integer updateBrowseNumByClickResourceLink(@Param("resourceId") Long resourceId,
                                               @Param("browseNum") Integer browseNum);


    /**
     * 根据资源id查询资源浏览量
     *
     * @param resourceId
     * @return
     */
    Integer getResourceBrowseNumById(Long resourceId);
}
