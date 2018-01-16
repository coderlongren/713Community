package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xian
 * @Description: 用户等级实体类
 * @Date:create in 2017/10/31 20:07
 */
public class UserRank implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String rankName;

    private Integer creditsLevel;

    private Date createTime;

    private Date updateTime;


    @Override
    public String toString() {
        return "UserRank{" +
                "id=" + id +
                ", rankName='" + rankName + '\'' +
                ", creditsLevel=" + creditsLevel +
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

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public Integer getCreditsLevel() {
        return creditsLevel;
    }

    public void setCreditsLevel(Integer creditsLevel) {
        this.creditsLevel = creditsLevel;
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
