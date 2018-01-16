package com.community.forum.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xian
 * @Description: 文章/帖文实体
 * @Date:create in 2017/12/16 14:37
 */
public class Article implements Serializable {

    private static final long serialVersionUID = 2542065268913075031L;

    private Long id;
    private String realname;
    private String username;
    private String img;
    private Date time;
    private String context;
    private String url;
    private String topic;
    private String title;
    private Integer pageview;
    private Integer checkFlag;
    private Integer showFlag;
    private Date createTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", realname='" + realname + '\'' +
                ", username='" + username + '\'' +
                ", img='" + img + '\'' +
                ", time=" + time +
                ", context='" + context + '\'' +
                ", url='" + url + '\'' +
                ", topic='" + topic + '\'' +
                ", title='" + title + '\'' +
                ", pageview=" + pageview +
                ", checkFlag=" + checkFlag +
                ", showFlag=" + showFlag +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getPageview() {
        return pageview;
    }

    public void setPageview(Integer pageview) {
        this.pageview = pageview;
    }

    public Integer getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(Integer checkFlag) {
        this.checkFlag = checkFlag;
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

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
