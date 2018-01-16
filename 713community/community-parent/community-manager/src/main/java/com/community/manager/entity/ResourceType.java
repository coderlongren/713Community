package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:王喜
 * @Description : 资源类别实体类
 * @Date: 2017/11/2 0002 21:29
 */
public class ResourceType implements Serializable {
    private Long id;

    private String name;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "ResourceType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
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

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
