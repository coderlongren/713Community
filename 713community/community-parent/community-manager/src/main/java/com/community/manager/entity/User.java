package com.community.manager.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xian
 * @Description: 网站用户实体类
 * @Date:create in 2017/10/31 15:53
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private String realname;

    private Long userTypeId;

    private String userClass;

    private String gender;

    private String email;

    private String phone;

    private String photo;

    private Integer status;

    private Long rankId;

    private Integer credits;

    private Date birthDay;

    private Date createTime;

    private Date updateTime;


    /***********以下是扩展字段********************/

    private String typeName;

    private String rankName;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", userTypeId=" + userTypeId +
                ", userClass='" + userClass + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", status=" + status +
                ", rankId=" + rankId +
                ", credits=" + credits +
                ", birthDay=" + birthDay +
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRankId() {
        return rankId;
    }

    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }
}
