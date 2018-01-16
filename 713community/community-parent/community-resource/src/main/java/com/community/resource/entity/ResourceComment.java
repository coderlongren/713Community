package com.community.resource.entity;

import com.community.resource.vo.ResourceCommentUserVo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:王喜
 * @Description: 资源评论实体
 * @Date:2017/11/16 19:05.
 */
public class ResourceComment implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long resourceId;

    private Long parentId;

    private Integer floor;

    private String username;

    private String img;

    private Date releaseTime;

    private String comment;

    private Date createTime;

    private Date updateTime;

    /**************扩展字段*******************/
    private User user;

    private ResourceCommentUserVo resourceCommentUserVo;

    @Override
    public String toString() {
        return "ResourceComment{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", parentId=" + parentId +
                ", floor=" + floor +
                ", username='" + username + '\'' +
                ", img='" + img + '\'' +
                ", releaseTime=" + releaseTime +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", user=" + user +
                ", resourceCommentUserVo=" + resourceCommentUserVo +
                '}';
    }

    public ResourceCommentUserVo getResourceCommentUserVo() {
        return resourceCommentUserVo;
    }

    public void setResourceCommentUserVo(ResourceCommentUserVo resourceCommentUserVo) {
        this.resourceCommentUserVo = resourceCommentUserVo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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
