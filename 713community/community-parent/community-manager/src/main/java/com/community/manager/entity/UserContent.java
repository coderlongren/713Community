package com.community.manager.entity;

import java.io.Serializable;

/**
 * @Author:王喜
 * @Description :用户内容实体
 * @Date: 2017/11/27 0027 19:18
 */
public class UserContent implements Serializable{

    private  Long id ;

    private Long userId;

    private String position;

    private String contact;

    private String about;

    private String skill;

    private String motto;

    private String education;

    private String language;

    private String hobby;

    private String award;

    private String workExp;

    @Override
    public String toString() {
        return "UserContent{" +
                "id=" + id +
                ", userId=" + userId +
                ", position='" + position + '\'' +
                ", contact='" + contact + '\'' +
                ", about='" + about + '\'' +
                ", skill='" + skill + '\'' +
                ", motto='" + motto + '\'' +
                ", education='" + education + '\'' +
                ", language='" + language + '\'' +
                ", hobby='" + hobby + '\'' +
                ", award='" + award + '\'' +
                ", workExp='" + workExp + '\'' +
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getWorkExp() {
        return workExp;
    }

    public void setWorkExp(String workExp) {
        this.workExp = workExp;
    }
}
