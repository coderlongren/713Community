package com.community.resource.dao;

import com.community.resource.entity.User;

/**
 * @Author:王喜
 * @Description :用户Dao
 * @Date: 2017/12/26 0026 23:25
 */
public interface UserDao {

    /**
     * 根据用户姓名查询用户信息
     *
     * @param username
     * @return
     */
    User getUserInfoByUsername(String username);
}
