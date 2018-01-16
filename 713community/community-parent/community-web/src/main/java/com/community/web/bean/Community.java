package com.community.web.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: huzisong
 * @Description: 社区信息实体类
 * @Date: 2017/11/2 19:18.
 */
public class Community implements Serializable {
    private Long id;
    private String introduction;
    private Date createTime;
    private Date updateTime;
    private String img;
    private String email;
    private String phone;
    private String address;

    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", introduction='" + introduction + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", img='" + img + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
