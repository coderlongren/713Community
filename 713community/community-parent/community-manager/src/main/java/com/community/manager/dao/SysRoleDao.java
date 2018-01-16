package com.community.manager.dao;

import com.community.manager.entity.SysRole;

/**
 * @Author: xian
 * @Description: 角色dao
 * @Date:create in 2017/10/25 15:31
 */
public interface SysRoleDao extends BaseDao<SysRole> {

    /**
     * 根据角色id查询角色-附带该角色所拥有的菜单权限
     *
     * @param roleId
     * @return
     */
    SysRole getRoleWithMenus(Long roleId);
}
