package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: xian
 * @Description: 角色实体
 * @Date:create in 2017/10/25 15:03
 */
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private String roleName;

    private Integer status;

    private String description;

    private Date createTime;

    private Date updateTime;

    private List<SysMenu> menus;


    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
    }
}
