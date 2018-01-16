package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:王喜
 * @Description :
 * @Date: 2017/11/6 0006 20:29
 */
public class ResourceInfo implements Serializable{
    private Long id;

    private Long resourceId;

    private String content;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "ResourceInfo{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", content='" + content + '\'' +
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

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
