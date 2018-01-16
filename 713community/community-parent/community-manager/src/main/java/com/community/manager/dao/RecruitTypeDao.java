package com.community.manager.dao;

import com.community.manager.entity.RecruitType;

import java.util.List;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/3 19:12
 */
public interface RecruitTypeDao extends BaseDao<RecruitType>{

    /**
     * 根据招新信息模糊搜索类型
     *
     * @param recruitType
     * @return
     */
    List<RecruitType> listTypeByInformation(String recruitType);
}



