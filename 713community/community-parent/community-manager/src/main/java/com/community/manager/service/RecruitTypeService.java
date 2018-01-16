package com.community.manager.service;

import com.community.manager.entity.RecruitType;
import com.community.manager.vo.PageResultVo;

import java.util.List;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/3 18:52
 */
public interface RecruitTypeService {

    /**
     * 新增招新信息
     *
     * @param recruitType
     * @return
     */
    Boolean insertRecruit(RecruitType recruitType);

    /**
     * 分页查询招新信息
     *
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listAllRecruitByPage(Integer page, Integer rows);

    /**
     * 更新招新信息
     *
     * @param typeId
     * @param recruitType
     * @return
     */
    Boolean updateRecruit(Long typeId, RecruitType recruitType);

    /**
     * 根据id查询招新信息
     *
     * @param typeId
     * @return
     */
    RecruitType getRecruitTypeById(Long typeId);

    /**
     * 根据Id删除招新信息
     *
     * @param typeId
     * @return
     */
    Boolean removeRecruitTypeById(Long typeId);

    /**
     * 根据招新信息模糊搜索类型
     *
     * @param recruitType
     * @param rows
     * @param page
     * @return
     */
    PageResultVo listTypeByInformation(String recruitType, Integer rows, Integer page);

    /**
     * 查询全部的招新类型
     *
     * @return
     */
    List<RecruitType> listAllRecruitType();
}
