package com.community.manager.service;

import com.community.manager.entity.Community;
import com.community.manager.vo.PageResultVo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 社区信息处理service层
 *
 * @Author: huzisong
 * @Description:
 * @Date: 2017/11/2 19:56.
 */
public interface CommunityService {
    /**
     * 分页查询社区信息
     *
     * @param page
     * @return 分页数据传输对象
     */
     PageResultVo viewCommunityByPage(Integer page);

    /**
     * 更新社区信息
     *
     * @param community
     */
     Boolean updateCommunity(Long id, Community community);


    /**
     * 根据id查询社区信息
     *
     * @param id
     * @return
     */
    Community getCommunityById(Long id);


    /**
     * 添加社区信息
     *
     * @param community
     */
    Boolean addCommunity(Community community);


    /**
     * 根据id删除社区信息
     *
     * @param id
     */
    Boolean deleteCommunityById(Long id);

    /**
     * 根据关键字搜索社区信息
     *
     * @param keyWord
     * @param page
     * @return
     */
    PageResultVo getCommunityByName(String keyWord, Integer page);

    /**
     * 查询社区信息
     *
     * @return
     */
    Community listAllCommunity();
}
