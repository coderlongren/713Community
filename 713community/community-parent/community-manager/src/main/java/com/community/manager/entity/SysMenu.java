package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xian
 * @Description: 菜单栏目实体
 * @Date:create in 2017/10/18 16:13
 */
public class SysMenu implements Serializable {

    private static final long serialVersionUID = -2744622628231472261L;

    private Long menuId;

    private Long parentId;

    private String menuName;

    private String url;

    private String icon;

    private String perms;

    private Integer type;

    private Integer orderNum;

    private Date createTime;

    private Date updateTime;


    //为了数据传输及页面显示，自定义的属性
    private String parentName;

    @Override
    public String toString() {
        return "SysMenu{" +
                "menuId=" + menuId +
                ", parentId=" + parentId +
                ", menuName='" + menuName + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", perms='" + perms + '\'' +
                ", type=" + type +
                ", orderNum=" + orderNum +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", parentName='" + parentName + '\'' +
                '}';
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
