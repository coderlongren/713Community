package com.community.web.service;

import com.community.web.bean.RecruitInfo;
import com.community.web.vo.RecruitAndImageVo;

import java.io.IOException;


/**
 * @Author : tingting
 * @Description : 加入我们首页Service层
 * @Date : 2017/11/27 11:09
 */
public interface RecruitService {

    /**
     * 访问加入我们首页
     *
     * @return
     */
    RecruitAndImageVo getImagesAndRecruitTypesVo() throws IOException;

    /**
     * 获取加入我们详情页的招新类型数据
     *
     * @return
     */
    RecruitInfo getRecruitInfoVo(Long typeId) throws IOException;
}
