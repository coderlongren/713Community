package com.community.manager.dao;

import com.community.manager.entity.Community;
import com.community.manager.vo.PageResultVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: huzisong
 * @Description:
 * @Date: 2017/11/2 20:29.
 */
public interface CommunityDao extends BaseDao<Community>{

    /**
     * 根据关键字进行模糊查询
     *
     * @param keyWord
     * @return
     */
    List<Community> listAllByName(String keyWord);
}
