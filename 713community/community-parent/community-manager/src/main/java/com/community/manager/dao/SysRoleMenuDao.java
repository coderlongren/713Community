package com.community.manager.dao;

import com.community.manager.entity.SysRoleMenu;

/**
 * @Author: xian
 * @Description:角色-菜单映射dao
 * @Date:create in 2017/10/25 15:32
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {

    /**
     * 根据角色id删除数据
     *
     * @param roleId
     * @return
     */
    Integer deleteByRoleId(Long roleId);
}
