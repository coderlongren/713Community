package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:王喜
 * @Description : 资源信息表的实体类
 * @Date: 2017/11/5 0005 15:57
 */
public class Resource implements Serializable{
    private Long id;

    //发布资源的用户id,关联用户表
    private Long userId;

    //资源的类型id，关联资源类型表
    private Long typeId;

    private String author;

    private Date releaseTime ;  //资源发布时间

    private Integer browseNum;

    private Integer downNum ;

    private String title;   //资源的标题

    private Integer showFlag;   //资源上下架的标志

    private Date createTime;

    private Date updateTime;

    /***********以下是扩展字段********************/

    //发布资源的用户名
    private String username;

    //资源的类别名称
    private String name;

    private String content;  //资源内容

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", userId=" + userId +
                ", typeId=" + typeId +
                ", author='" + author + '\'' +
                ", releaseTime=" + releaseTime +
                ", browseNum=" + browseNum +
                ", downNum=" + downNum +
                ", title='" + title + '\'' +
                ", showFlag=" + showFlag +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Integer getDownNum() {
        return downNum;
    }

    public void setDownNum(Integer downNum) {
        this.downNum = downNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
