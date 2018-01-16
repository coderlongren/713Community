package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/17 20:10
 */
public class MemberDownload implements Serializable{

    private static final long serialVersionUID = -4886049L;

    private Long id;

    private String downloader;

    private String sharer;

    private String ip;

    private String url;

    private String resourceTitle;

    private String resourceType;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "MemberDownload{" +
                "id=" + id +
                ", downloader='" + downloader + '\'' +
                ", sharer='" + sharer + '\'' +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", resourceTitle='" + resourceTitle + '\'' +
                ", resourceType='" + resourceType + '\'' +
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

    public String getDownloader() {
        return downloader;
    }

    public void setDownloader(String downloader) {
        this.downloader = downloader;
    }

    public String getSharer() {
        return sharer;
    }

    public void setSharer(String sharer) {
        this.sharer = sharer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResourceTitle() {
        return resourceTitle;
    }

    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
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
