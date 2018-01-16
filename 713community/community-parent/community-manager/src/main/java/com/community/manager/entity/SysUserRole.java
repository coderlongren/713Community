package com.community.manager.entity;

/**
 * @Author: xian
 * @Description: 管理员用户-角色映射实体
 * @Date:create in 2017/10/28 11:22
 */
public class SysUserRole {

    //实体类的属性字段全部定义为private，类型全部不用基本类型，另外每个实体类都应该有每个字段的getter/setter方法，toString方法

    private Long id;

    private Long sysUserId;

    private Long roleId;

    @Override
    public String toString() {
        return "SysUserRole{" +
                "id=" + id +
                ", sysUserId=" + sysUserId +
                ", roleId=" + roleId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
