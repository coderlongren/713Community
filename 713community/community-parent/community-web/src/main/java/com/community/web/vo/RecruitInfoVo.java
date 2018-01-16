package com.community.web.vo;

import com.community.web.bean.RecruitInfo;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/28 20:03
 */
public class RecruitInfoVo {

    private RecruitInfo recruitInfo;

    public RecruitInfoVo() {
    }
    
    public RecruitInfoVo(RecruitInfo recruitInfo) {
        this.recruitInfo = recruitInfo;
    }

    public RecruitInfo getRecruitInfo() {
        return recruitInfo;
    }

    public void setRecruitInfo(RecruitInfo recruitInfo) {
        this.recruitInfo = recruitInfo;
    }
}
