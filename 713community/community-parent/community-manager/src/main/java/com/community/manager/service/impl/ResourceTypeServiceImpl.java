package com.community.manager.service.impl;

import com.community.manager.dao.ResourceTypeDao;
import com.community.manager.entity.ResourceType;
import com.community.manager.service.ResourceTypeService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author:王喜
 * @Description :资源类别service接口实现类
 * @Date: 2017/11/2 0002 21:54
 */
@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {
    //注入ResourceTypeDao
    @Autowired
    private ResourceTypeDao resourceTypeDao;

    /**
     * 分页查询资源类别
     *
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAllResourceTypeByPage(Integer page) {
        if (page <= 0 || null == page) {
            page = 1;
        }
        int rows = 10;
        /*
         * page:表示下一页
         * rows:表示每一页显示的记录数
         */
        PageHelper.startPage(page, rows);
        List<ResourceType> list=resourceTypeDao.listAll();

        PageInfo<ResourceType> pageinfo=new PageInfo<>(list);
        return new PageResultVo(pageinfo.getTotal(),pageinfo.getList(),pageinfo.getPages());
    }


    /**
     * 根据id查询资源类别
     *
     * @param resouceTypeId
     * @return
     */
    @Override
    public ResourceType getResourceTypeById(Long resouceTypeId) {
        return resourceTypeDao.getById(resouceTypeId);
    }

    /**
     * 根据id修改资源类型
     *
     * @param resourceTypeId
     * @param resourceType
     * @return
     */
    @Override
    public Boolean updateResourceTypeById(Long resourceTypeId, ResourceType resourceType) {
        //添加时间属性，不会再前端页面进行展示填写时间的框
       resourceType.setUpdateTime(new Date());
       resourceType.setId(resourceTypeId);
       return resourceTypeDao.update(resourceType) == 1;
    }


    /**
     * 根据id删除资源类别
     *
     * @param resourceTypeId
     * @return
     */
    @Override
    public Boolean removeResourceTypeById(Long resourceTypeId) {
        return resourceTypeDao.deleteById(resourceTypeId) == 1;
    }


    /**
     * 增加资源类别
     *
     * @param resourceType
     * @return
     */
    @Override
    public Boolean insertResourceType(ResourceType resourceType) {
        //为resourceType设置时间属性
        resourceType.setCreateTime(new Date());
        resourceType.setUpdateTime(new Date());
        return resourceTypeDao.insert(resourceType) == 1;
    }

    @Override
    public PageResultVo listResourceTypeWithSearch(String searchParam, Integer page) {
        if (page <= 0 || null == page) {
            page = 1;
        }
        int rows = 10;
        /*
         * page:表示下一页
         * rows:表示每一页显示的记录数
         */
        //拼接模糊查询的字符串，，拼接%，不在mapper中进行拼接
        searchParam = new StringBuilder("%").append(searchParam).append("%").toString();

        PageHelper.startPage(page, rows);
        List<ResourceType> list = resourceTypeDao.listResourceTypeWithSearch(searchParam);

        PageInfo<ResourceType> pageinfo= new PageInfo<>(list);
        return new PageResultVo(pageinfo.getTotal(),pageinfo.getList(),pageinfo.getPages());
    }
}
