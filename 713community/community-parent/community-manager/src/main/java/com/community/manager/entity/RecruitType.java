package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/3 18:27
 */
public class RecruitType implements Serializable{

    private static final long serialVersionUID = 8817410339447556529L;

    private Long id;

    private String recruitName;

    private String remark;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "RecruitType{" +
                "id=" + id +
                ", recruitName='" + recruitName + '\'' +
                ", recruitName='" + remark + '\'' +
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

    public String getRecruitName() {
        return recruitName;
    }

    public void setRecruitName(String recruitName) {
        this.recruitName = recruitName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
