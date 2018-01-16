package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:王喜
 * @Description : 任务信息表
 * @Date: 2017/11/16 0016 20:25
 */
public class Task implements Serializable{

    private  Long id;

    private String produceName;

    private String consumeName;

    private Date releaseTime;

    private Date commitTime;

    private String content;

    private String title;

    private Integer credits;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", produceName='" + produceName + '\'' +
                ", consumeName='" + consumeName + '\'' +
                ", releaseTime=" + releaseTime +
                ", commitTime=" + commitTime +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                ", status=" + status +
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

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public String getConsumeName() {
        return consumeName;
    }

    public void setConsumeName(String consumeName) {
        this.consumeName = consumeName;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
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

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
