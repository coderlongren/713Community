package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/11/4 21:11
 */
public class Image implements Serializable {

    private Long id;

    private String imageName;

    private String title;

    private String info;

    private String url;

    private String linkUrl;

    private Long typeId;

    private Integer orderNum;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    /**********扩展字段****************/
    private String typeName;


    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageName='" + imageName + '\'' +
                ", title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", url='" + url + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", typeId=" + typeId +
                ", orderNum=" + orderNum +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
