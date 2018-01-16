package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xian
 * @Description:图片类型实体
 * @Date:create in 2017/11/3 16:48
 */
public class ImageType implements Serializable {

    private static final long serialVersionUID = 9146373532601140852L;

    private Long id;

    private String typeName;

    private Date createTime;

    private Date updateTime;


    @Override
    public String toString() {
        return "ImageType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
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
