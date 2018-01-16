package com.community.web.vo;


import com.community.web.bean.UserContent;

import java.util.List;

/**
 * @Author:王喜
 * @Description :用户内容Vo(用于增加用户内容时使用。)
 * @Date: 2017/11/27 0027 19:47
 */
public class UserContentVo {

    private Long id;

    private Long userId;

    private String position;

    private String about;

    private String motto;

    //存储用户联系方式vo
    private List<UserContactVo> contact;

    private List<UserSkillVo> skill;

    private List<UserLanguageVo> language;

    private List<UserAwardVo> award;

    private List<UserHobbyVo> hobby;

    private List<UserWorkExpVo> work_exp;

    private List<UserEducationVo> education;


    public UserContentVo() {
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public List<UserContactVo> getContact() {
        return contact;
    }

    public void setContact(List<UserContactVo> contact) {
        this.contact = contact;
    }

    public List<UserSkillVo> getSkill() {
        return skill;
    }

    public void setSkill(List<UserSkillVo> skill) {
        this.skill = skill;
    }

    public List<UserLanguageVo> getLanguage() {
        return language;
    }

    public void setLanguage(List<UserLanguageVo> language) {
        this.language = language;
    }

    public List<UserAwardVo> getAward() {
        return award;
    }

    public void setAward(List<UserAwardVo> award) {
        this.award = award;
    }

    public List<UserHobbyVo> getHobby() {
        return hobby;
    }

    public void setHobby(List<UserHobbyVo> hobby) {
        this.hobby = hobby;
    }

    public List<UserWorkExpVo> getWork_exp() {
        return work_exp;
    }

    public void setWork_exp(List<UserWorkExpVo> work_exp) {
        this.work_exp = work_exp;
    }

    public List<UserEducationVo> getEducation() {
        return education;
    }

    public void setEducation(List<UserEducationVo> education) {
        this.education = education;
    }
}
