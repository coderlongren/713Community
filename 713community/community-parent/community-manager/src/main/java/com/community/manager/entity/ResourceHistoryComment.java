package com.community.manager.entity;

import java.util.Date;

/**
 * @Author:王喜
 * @Description: 资源历史评论实体
 * @Date:2017/11/16 19:05.
 */
public class ResourceHistoryComment {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long parentId;

    private Integer floor;

    private String username;

    private String img;

    private Date releaseTime;

    private String comment;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "ResourceHistoryComment{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", floor=" + floor +
                ", username='" + username + '\'' +
                ", img='" + img + '\'' +
                ", releaseTime=" + releaseTime +
                ", comment='" + comment + '\'' +
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
