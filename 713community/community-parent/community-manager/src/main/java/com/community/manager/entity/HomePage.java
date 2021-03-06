package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : tingting
 * @Description : 网站首页导航栏类
 * @Date : 2017/11/3 18:27
 */
public class HomePage implements Serializable{

    private static final long serialVersionUID = 886529L;

    private Long id;

    private String columnName;

    private String url;

    private Integer orderNum;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "HomePage{" +
                "id=" + id +
                ", columnName='" + columnName + '\'' +
                ", url='" + url + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

}
