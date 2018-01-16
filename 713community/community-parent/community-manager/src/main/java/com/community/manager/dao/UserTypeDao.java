package com.community.manager.dao;

import com.community.manager.entity.UserType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/10/31 20:14
 */
public interface UserTypeDao extends BaseDao<UserType> {

    /**
     * 模糊查询用户类型
     *
     * @param searchVal
     * @return
     */
    List<UserType> listUserTypeWithSearch(@Param("searchVal") String searchVal);

}
