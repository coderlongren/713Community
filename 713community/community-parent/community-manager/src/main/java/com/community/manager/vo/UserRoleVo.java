package com.community.manager.vo;

import com.community.manager.entity.SysRole;
import com.community.manager.entity.SysUser;

import java.util.List;

/**
 * @Author: xian
 * @Description: 系统用户与角色对象集合的传输对象
 * @Date:create in 2017/10/28 20:16
 */
public class UserRoleVo {

    private SysUser user;

    private List<SysRole> roles;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
