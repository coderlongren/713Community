package com.community.manager.service.impl;

import com.community.manager.dao.HomePageDao;
import com.community.manager.dao.RecruitInfoDao;
import com.community.manager.dao.RecruitTypeDao;
import com.community.manager.entity.HomePage;
import com.community.manager.entity.RecruitInfo;
import com.community.manager.entity.RecruitType;
import com.community.manager.service.HomePageService;
import com.community.manager.service.RecruitInfoService;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.RecruitAndTypeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/6 21:23
 */
@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private HomePageDao homePageDao;

    /**
     * 新增首页导航信息
     *
     * @param homePage
     * @return
     */
    @Override
    public Boolean insertHomePageInfo(HomePage homePage) {
        homePage.setCreateTime(new Date());
        homePage.setUpdateTime(new Date());

        return homePageDao.insert(homePage) == 1;
    }

    /**
     * 更新首页导航信息
     *
     * @param typeId
     * @param homePage
     * @return
     */
    @Override
    public Boolean updateHomePageInfo(Long typeId, HomePage homePage) {
        homePage.setId(typeId);
        homePage.setUpdateTime(new Date());

        return homePageDao.update(homePage) == 1;
    }

    /**
     * 根据id删除首页导航信息
     *
     * @param typeId
     * @return
     */
    @Override
    public Boolean deleteHomePageInfoById(Long typeId) {
        return homePageDao.deleteById(typeId) == 1;
    }

    /**
     * 根据id查询首页导航信息
     *
     * @param typeId
     * @return
     */
    @Override
    public HomePage getHomePageInfoById(Long typeId) {
        return homePageDao.getById(typeId);
    }

    /**
     * 分页查询首页导航信息
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listAllHomePageInfoByPage(Integer page, Integer rows) {
        if (page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page, rows);

        List<HomePage> list = homePageDao.listAll();
        PageInfo<HomePage> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 根据首页栏目导航名模糊查询名称
     *
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listHomePageWithSearch(String searchVal, Integer page, Integer rows) {
        if (page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page, rows);

        String val = new StringBuilder("%").append(searchVal).append("%").toString();
        List<HomePage> list = homePageDao.listHomePageWithSearch(val);
        PageInfo<HomePage> pageInfo = new PageInfo<>(list);

        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }
}
