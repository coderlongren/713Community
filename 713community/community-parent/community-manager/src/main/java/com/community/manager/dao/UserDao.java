package com.community.manager.dao;

import com.community.manager.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/10/31 16:47
 */
public interface UserDao extends BaseDao<User> {
    List<User> listUserWithSearch(@Param("searchVal") String searchVal);
}
