package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/6 19:19
 */
public class RecruitInfo implements Serializable{

    private static final long serialVersionUID = -4882748288604905806L;

    private Long id;

    private Long typeId;

    private String duty;

    private String demand;

    private String linkMan;

    private String linkQQ;

    private Date createTime;

    private Date updateTime;

    /*******************扩展字段*********************/

    private String typeName;

    @Override
    public String toString() {
        return "RecruitInfo{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", duty='" + duty + '\'' +
                ", demand='" + demand + '\'' +
                ", linkMan='" + linkMan + '\'' +
                ", linkQQ='" + linkQQ + '\'' +
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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkQQ() {
        return linkQQ;
    }

    public void setLinkQQ(String linkQQ) {
        this.linkQQ = linkQQ;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
