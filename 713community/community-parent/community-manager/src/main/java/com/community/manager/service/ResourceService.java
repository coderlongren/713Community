package com.community.manager.service;

import com.community.manager.entity.Resource;
import com.community.manager.vo.AddResourceVo;
import com.community.manager.vo.PageResultVo;

/**
 * @Author:王喜
 * @Description :  资源信息Service接口
 * @Date: 2017/11/5 0005 16:09
 */
public interface ResourceService {
    /**
     * 分页查询资源信息
     *
     * @param page
     * @return
     */
    PageResultVo listAllResourceByPage(Integer page);

    /**
     * 根据id更新资源信息
     *
     * @param resourceId
     * @param showFlag
     * @return
     */
    Boolean updateResourceById(Long resourceId, Integer showFlag);

    /**
     * 根据id删除资源
     *
     * @param resourceId
     * @return
     */
    Boolean removeResourceById(Long resourceId);

    /**
     * 查询添加资源下拉框的信息
     *
     * @return
     */
    AddResourceVo listAllAddResourceVo();

    /**
     * 添加资源信息
     *
     * @param resource
     * @return
     */
    Boolean insertResource(Resource resource);

    /**
     * 根据条件模糊搜索资源信息
     *
     * @param searchKeywords  搜索关键字
     * @param searchParam  搜索条件
     * @param page  下一页
     * @return
     */
    PageResultVo listResourceWithSearch(String searchKeywords, String searchParam, Integer page);
}
