package com.community.manager.entity;

import java.util.Date;

/**
 * @Author: huzisong
 * @Description:
 * @Date: 2017/11/4 19:37.
 */
public class Notice {
    private Long id;
    private String userName;
    private Date releaseTime;
    private int status;
    private String content;
    private String title;
    private Date createTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", releaseTime=" + releaseTime +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
