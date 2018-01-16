package com.community.manager.dao;

import com.community.manager.entity.UserRank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/10/31 20:14
 */
public interface UserRankDao extends BaseDao<UserRank> {
    /**
     * 根据用户等级名称模糊查询
     * @param searchVal
     * @return
     */
    List<UserRank> listUserRankWithSearch(@Param("searchVal") String searchVal);
}
