package com.community.manager.dao;

import com.community.manager.entity.SysUser;

import java.util.List;

/**
 * @Author: xian
 * @Description: 系统管理员用户dao
 * @Date:create in 2017/10/18 11:41
 */
public interface SysUserDao extends BaseDao<SysUser> {

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    SysUser getByUserName (String userName);

    /**
     * 根据系统用户id查询数据，并附带角色信息
     *
     * @param userId
     * @return
     */
    SysUser getByIdWithRoles(Long userId);

    /**
     * 根据管理员名称模糊查询用户
     *
     * @param userName
     * @return
     */
    List<SysUser> listUserByName(String userName);

}
