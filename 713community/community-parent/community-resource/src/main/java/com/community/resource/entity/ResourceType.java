package com.community.resource.entity;

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

    private String url;  //存储资源类别图片的路径

    private String briefInfo;

    private String description;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "ResourceType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", briefInfo='" + briefInfo + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
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
