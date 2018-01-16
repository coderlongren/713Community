package com.community.web.vo;

import com.community.web.bean.Achievement;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @Author:王喜
 * @Description : 存储项目列表和项目成员列表的Vo
 * @Date: 2017/11/15 0015 15:59
 */
public class AchievementMemberVo {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //项目对象
    private Achievement achievement;

    //存储项目成员的name和url
    private List<MembersVo> membersVoList;

    //存储membersVoList的size
    private int membersVoListSize;

    public int getMembersVoListSize() {
        return membersVoListSize;
    }

    public void setMembersVoListSize(int membersVoListSize) {
        this.membersVoListSize = membersVoListSize;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public List<MembersVo> getMembersVoList() {
        return membersVoList;
    }

    public void setMembersVoList(List<MembersVo> membersVoList) {
        this.membersVoList = membersVoList;
    }

    public AchievementMemberVo() {
    }

}
