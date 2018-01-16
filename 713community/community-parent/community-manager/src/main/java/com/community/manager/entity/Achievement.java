package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:王喜
 * @Description : 项目成果展示实体类
 * @Date: 2017/11/14 0014 16:56
 */
public class Achievement implements Serializable{

    private Long id;

    private String name;

    private String bigintroduction;

    private String members;

    private String type;

    private String cooperation;

    private Integer showFlag;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bigintroduction='" + bigintroduction + '\'' +
                ", members='" + members + '\'' +
                ", type='" + type + '\'' +
                ", cooperation='" + cooperation + '\'' +
                ", showFlag=" + showFlag +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
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

    public String getBigintroduction() {
        return bigintroduction;
    }

    public void setBigintroduction(String bigintroduction) {
        this.bigintroduction = bigintroduction;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getCooperation() {
        return cooperation;
    }

    public void setCooperation(String cooperation) {
        this.cooperation = cooperation;
    }

    public Integer getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
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
