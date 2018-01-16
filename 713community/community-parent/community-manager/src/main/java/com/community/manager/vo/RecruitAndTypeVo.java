package com.community.manager.vo;

import com.community.manager.entity.RecruitInfo;
import com.community.manager.entity.RecruitType;

import java.util.List;

/**
 * @Author : tingting
 * @Description :招新详情信息和类别列表传输对象
 * @Date : 2017/11/9 19:50
 */
public class RecruitAndTypeVo {

    private RecruitInfo recruitInfo;

    private List<RecruitType> recruitTypes;

    public RecruitAndTypeVo(RecruitInfo recruitInfo, List<RecruitType> recruitTypes) {
        this.recruitInfo = recruitInfo;
        this.recruitTypes = recruitTypes;
    }

    public RecruitInfo getRecruitInfo() {
        return recruitInfo;
    }

    public void setRecruitInfo(RecruitInfo recruitInfo) {
        this.recruitInfo = recruitInfo;
    }

    public List<RecruitType> getRecruitTypes() {
        return recruitTypes;
    }

    public void setRecruitTypes(List<RecruitType> recruitTypes) {
        this.recruitTypes = recruitTypes;
    }
}
