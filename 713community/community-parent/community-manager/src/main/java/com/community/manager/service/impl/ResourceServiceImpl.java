package com.community.manager.service.impl;

import com.community.manager.dao.ResourceDao;
import com.community.manager.dao.ResourceInfoDao;
import com.community.manager.dao.ResourceTypeDao;
import com.community.manager.dao.UserDao;
import com.community.manager.entity.Resource;
import com.community.manager.entity.ResourceInfo;
import com.community.manager.entity.ResourceType;
import com.community.manager.entity.User;
import com.community.manager.service.ResourceService;
import com.community.manager.vo.AddResourceVo;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author:王喜
 * @Description : ResourceService 资源信息接口实现类
 * @Date: 2017/11/5 0005 16:09
 */
@Service
public class ResourceServiceImpl implements ResourceService{
    //注入Dao对象
    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private ResourceInfoDao resourceInfoDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ResourceTypeDao resourceTypeDao;
    /**
     * 分页查询资源信息
     *
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAllResourceByPage(Integer page) {
        if (page <= 0 || null == page) {
            page = 1;
        }
        int rows = 10;
        /*
         * page:表示下一页
         * rows:表示每一页显示的记录数
         */
        PageHelper.startPage(page, rows);
        List<Resource> list=resourceDao.listAll();

        PageInfo<Resource> pageinfo=new PageInfo<>(list);
        return new PageResultVo(pageinfo.getTotal(),pageinfo.getList(),pageinfo.getPages());
    }


    /**
     * 根据id更新资源信息
     *
     * @param resourceId
     * @param showFlag
     * @return
     */
    @Override
    public Boolean updateResourceById(Long resourceId, Integer showFlag) {
        return resourceDao.updateResourceById(resourceId,showFlag) == 1;
    }

    /**
     * 根据id删除资源
     *
     * @param resourceId
     * @return
     */
    @Override
    public Boolean removeResourceById(Long resourceId) {
        //删除资源表记录
        Integer resourceResult = resourceDao.deleteById(resourceId);

        //删除资源详情表的记录
        Integer resourceInfoResult = resourceInfoDao.deleteById(resourceId);

        if (resourceResult == 1 && resourceInfoResult == 1){
            return true;
        }
        return false;
    }

    /**
     * 查询添加资源下拉框的信息
     *
     * @return
     */
    @Override
    public AddResourceVo listAllAddResourceVo() {
        List<User> userList = userDao.listAll();
        List<ResourceType> resourceTypeList = resourceTypeDao.listAll();

        return new AddResourceVo(userList,resourceTypeList);
    }

    /**
     * 添加资源信息
     *
     * @param resource
     * @return
     */
    @Override
    public Boolean insertResource(Resource resource) {
        ResourceInfo resourceInfo = new ResourceInfo();

        //默认增加的资源是上架的
        resource.setShowFlag(1);
        resource.setReleaseTime(new Date());
        resource.setBrowseNum(20);
        resource.setDownNum(30);
        resource.setCreateTime(new Date());
        resource.setUpdateTime(new Date());
        //对资源表添加记录
        Integer resourceResult = resourceDao.insert(resource);

        //对资源详情表添加记录
            //获取resource_id
            resourceInfo.setResourceId(resource.getId());

            //获得content
            resourceInfo.setContent(resource.getContent());
            resourceInfo.setCreateTime(new Date());
            resourceInfo.setUpdateTime(new Date());
        Integer resourceInfoResult = resourceInfoDao.insert(resourceInfo);

        if (resourceResult == 1 && resourceInfoResult == 1){
            return true;
        }
        return false;

    }

    /**
     * 根据条件模糊搜索资源信息
     *
     * @param searchKeywords 搜索关键字
     * @param searchParam    搜索条件
     * @param page           下一页
     * @return
     */
    @Override
    public PageResultVo listResourceWithSearch(String searchKeywords, String searchParam, Integer page) {
        if (page <= 0 || null == page) {
            page = 1;
        }
        int rows = 10;
        /*
         * page:表示下一页
         * rows:表示每一页显示的记录数
         */
        //拼接模糊查询的字符串，，拼接%，不在mapper中进行拼接,对关键字进行拼接
        searchKeywords = new StringBuilder("%").append(searchKeywords).append("%").toString();

        PageHelper.startPage(page, rows);
        List<Resource> list = resourceDao.listResourceWithSearch(searchParam,searchKeywords);

        PageInfo<Resource> pageinfo= new PageInfo<>(list);
        return new PageResultVo(pageinfo.getTotal(),pageinfo.getList(),pageinfo.getPages());
    }
}
