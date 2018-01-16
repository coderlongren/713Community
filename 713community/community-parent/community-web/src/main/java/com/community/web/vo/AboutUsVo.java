package com.community.web.vo;

import com.community.web.bean.Community;

import java.util.List;

/**
 * @Author:王喜
 * @Description :关于我们页面Vo
 * @Date: 2017/11/26 0026 20:43
 */
public class AboutUsVo {

    //社区信息
    private Community community;

    //项目成果信息
    private List<AchievementMemberVo> achievements;


    public AboutUsVo() {

    }

    public AboutUsVo(Community community, List<AchievementMemberVo> achievements) {
        this.community = community;
        this.achievements = achievements;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public List<AchievementMemberVo> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<AchievementMemberVo> achievements) {
        this.achievements = achievements;
    }
}
