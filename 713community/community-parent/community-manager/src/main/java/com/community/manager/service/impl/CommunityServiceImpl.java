package com.community.manager.service.impl;

import com.community.manager.dao.CommunityDao;
import com.community.manager.entity.Community;
import com.community.manager.service.CommunityService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: huzisong
 * @Description:
 * @Date: 2017/11/2 20:24.
 */
@Service
public class CommunityServiceImpl implements CommunityService{
    @Autowired
    private CommunityDao communityDao;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Override
    public PageResultVo viewCommunityByPage(Integer page) {
        //为了提升系统的健壮性，先判断page是否为空，为空则设一个默认值1
        if (page == null || page <= 0) {
            page = 1;
        }

        int rows = 10;

        PageHelper.startPage(page, rows);
        List<Community> list = communityDao.listAll();


        PageInfo<Community> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 更新社区信息
     *
     * @param id
     * @param community
     * @return
     */
    @Override
    public Boolean updateCommunity(Long id, Community community) {
        community.setId(id);
        community.setUpdateTime(new Date());
        return communityDao.update(community) == 1;
    }

    /**
     *添加社区信息
     *
     * @param community
     * @return
     */
    @Override
    public Boolean addCommunity(Community community) {
        community.setCreateTime(new Date());
        community.setUpdateTime(new Date());
        return communityDao.insert(community) == 1;
}

    /**
     *根据id查询社区信息
     *
     * @param id
     * @return
     */
    @Override
    public Community getCommunityById(Long id) {
        return communityDao.getById(id);
    }

    /**
     * 删除社区信息
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deleteCommunityById(Long id) {
        return communityDao.deleteById(id) == 1;
    }

    /**
     * 根据关键字查询社区信息
     *
     * @param keyWord
     * @param page
     * @return
     */
    @Override
    public PageResultVo getCommunityByName(String keyWord, Integer page) {
        //为了提升系统的健壮性，先判断page是否为空，为空则设一个默认值1
        if (page == null || page <= 0) {
            page = 1;
        }

        //默认显示10条数据
        int rows = 10;

        //已下两条语句在一起处理分页查询
        PageHelper.startPage(page, rows);
        String value = new StringBuilder("%").append(keyWord).append("%").toString();
        List<Community> list = communityDao.listAllByName(value);

        PageInfo<Community> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }


    /**
     * 查询社区信息
     *
     * @return
     */
    @Override
    public Community listAllCommunity() {

        List<Community> list = new ArrayList<>();
        list = communityDao.listAll();
        Community community = new Community();
        community = list.get(0);

        return community;
    }
}
