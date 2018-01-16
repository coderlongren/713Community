package com.community.web.vo;

import com.community.web.bean.Image;

import java.util.List;

/**
 * @Author:chenfq
 * @Description:  生活点滴的Vo类型
 * @Date:2017/11/26 21:11.
 */
public class TeamLifeVo {

    List<Image> teamLifeTopImgs;
    List<Image> teamLifeButtonImgs;

    public List<Image> getTeamLifeTopImgs() {
        return teamLifeTopImgs;
    }

    public void setTeamLifeTopImgs(List<Image> teamLifeTopImgs) {
        this.teamLifeTopImgs = teamLifeTopImgs;
    }

    public List<Image> getTeamLifeButtonImgs() {
        return teamLifeButtonImgs;
    }

    public TeamLifeVo(List<Image> teamLifeTopImgs, List<Image> teamLifeButtonImgs) {
        this.teamLifeTopImgs = teamLifeTopImgs;
        this.teamLifeButtonImgs = teamLifeButtonImgs;
    }

    public void setTeamLifeButtonImgs(List<Image> teamLifeButtonImgs) {
        this.teamLifeButtonImgs = teamLifeButtonImgs;
    }
}
