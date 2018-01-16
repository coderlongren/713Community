package com.community.web.vo;

import com.community.web.bean.Image;
import com.community.web.bean.RecruitType;

import java.util.List;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/27 9:25
 */
public class RecruitAndImageVo {

    private List<Image> recruitTypeImgs;

    private List<RecruitType> recruitTypes;

    public RecruitAndImageVo() {
    }

    public RecruitAndImageVo(List<Image> recruitTypeImgs, List<RecruitType> recruitTypes) {
        this.recruitTypeImgs = recruitTypeImgs;
        this.recruitTypes = recruitTypes;
    }

    public List<Image> getRecruitTypeImgs() {
        return recruitTypeImgs;
    }

    public void setRecruitTypeImgs(List<Image> recruitTypeImgs) {
        this.recruitTypeImgs = recruitTypeImgs;
    }

    public List<RecruitType> getRecruitTypes() {
        return recruitTypes;
    }

    public void setRecruitTypes(List<RecruitType> recruitTypes) {
        this.recruitTypes = recruitTypes;
    }
}
