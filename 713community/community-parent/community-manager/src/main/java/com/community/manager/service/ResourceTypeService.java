package com.community.manager.service;

import com.community.manager.entity.ResourceType;
import com.community.manager.vo.PageResultVo;

/**
 * @Author:王喜
 * @Description :资源类别service接口
 * @Date: 2017/11/2 0002 21:53
 */
public interface ResourceTypeService {
    /**
     * 分页查询资源类别
     *
     * @param page
     * @return
     */
    PageResultVo listAllResourceTypeByPage(Integer page);

    /**
     * 根据id查询资源类别信息
     *
     * @param resouceTypeId
     * @return
     */
    ResourceType getResourceTypeById(Long resouceTypeId);

    /**
     * 更新资源类别
     *
     * @param resourceTypeId
     * @param resourceType
     * @return
     */
    Boolean updateResourceTypeById(Long resourceTypeId, ResourceType resourceType);

    /**
     * 根据id删除资源类别
     *
     * @param resourceTypeId
     * @return
     */
    Boolean removeResourceTypeById(Long resourceTypeId);


    /**
     * 增加资源类别
     *
     * @param resourceType
     * @return
     */
    Boolean insertResourceType(ResourceType resourceType);


    /**
     * 根据条件分页查询
     * @param searchParam  查询条件
     * @param page   下一页
     * @return
     */
    PageResultVo listResourceTypeWithSearch(String searchParam, Integer page);







}
