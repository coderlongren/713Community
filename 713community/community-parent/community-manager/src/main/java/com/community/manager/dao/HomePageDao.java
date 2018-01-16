package com.community.manager.dao;

import com.community.manager.entity.HomePage;
import com.community.manager.entity.RecruitInfo;

import java.util.List;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/6 21:24
 */
public interface HomePageDao extends BaseDao<HomePage> {

    /**
     * 根据首页栏目导航名模糊查询名称
     *
     * @param searchVal
     * @return
     */
    List<HomePage> listHomePageWithSearch(String searchVal);
}
