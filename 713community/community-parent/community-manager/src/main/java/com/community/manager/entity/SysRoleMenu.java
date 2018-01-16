package com.community.manager.entity;

import java.io.Serializable;

/**
 * @Author: xian
 * @Description: 角色-菜单映射实体
 * @Date:create in 2017/10/25 15:10
 */
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long roleId;

    private Long menuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
