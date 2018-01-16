package com.community.manager.service;

import com.community.manager.entity.RecruitInfo;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.RecruitAndTypeVo;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/6 21:23
 */
public interface RecruitInfoService {

    /**
     * 新增招新标准详情信息
     *
     * @param recruitInfo
     * @return
     */
    Boolean insertRecruitInfo(RecruitInfo recruitInfo);

    /**
     * 更新招新标准详情信息
     *
     * @param typeId
     * @param recruitInfo
     * @return
     */
    Boolean updateRecruitInfo(Long typeId, RecruitInfo recruitInfo);


    /**
     * 根据id删除招新标准详情信息
     *
     * @param typeId
     * @return
     */
    Boolean deleteRecruitInfoById(Long typeId);

    /**
     * 分页查询招新标准详情信息
     *
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listAllRecruitInfoByPage(Integer page, Integer rows);

    /**
     * 根据id查询招新标准详情信息
     *
     * @param infoId
     * @return
     */
    RecruitAndTypeVo getRecruitInfoById(Long infoId);

    /**
     * 分页模糊搜索招查询新标准详情信息
     *
     * @param recruitInfo
     * @param rows
     * @param page
     * @return
     */
    /*PageResultVo listInfoByInformation(String recruitInfo, Integer rows, Integer page);*/
}
