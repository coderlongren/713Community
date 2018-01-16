package com.community.manager.service;

import com.community.manager.entity.HomePage;
import com.community.manager.entity.RecruitInfo;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.RecruitAndTypeVo;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/6 21:23
 */
public interface HomePageService {

    /**
     * 新增首页导航信息
     *
     * @param homePage
     * @return
     */
    Boolean insertHomePageInfo(HomePage homePage);

    /**
     * 更新首页导航信息
     *
     * @param typeId
     * @param homePage
     * @return
     */
    Boolean updateHomePageInfo(Long typeId, HomePage homePage);

    /**
     * 根据id删除首页导航信息
     *
     * @param typeId
     * @return
     */
    Boolean deleteHomePageInfoById(Long typeId);

    /**
     * 根据id查询首页导航信息
     *
     * @param typeId
     * @return
     */
    HomePage getHomePageInfoById(Long typeId);

    /**
     * 分页查询首页导航信息
     *
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listAllHomePageInfoByPage(Integer page, Integer rows);

    /**
     * 根据首页栏目导航名模糊查询名称
     *
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listHomePageWithSearch(String searchVal, Integer page, Integer rows);
}
