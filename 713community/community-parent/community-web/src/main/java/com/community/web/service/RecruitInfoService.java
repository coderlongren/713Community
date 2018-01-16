package com.community.web.service;

import com.community.web.bean.RecruitInfo;

import java.io.IOException;

/**
 * @Author : tingting
 * @Description : 加入我们详情页Service层
 * @Date : 2017/11/28 20:05
 */
public interface RecruitInfoService {

    /**
     * 获取加入我们详情页的招新类型数据
     *
     * @return
     */
    RecruitInfo getRecruitInfoVo(Long typeId) throws IOException;
}
