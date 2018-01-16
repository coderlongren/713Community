package com.community.manager.service;

import com.community.manager.entity.Achievement;
import com.community.manager.vo.AchievementMemberVo;
import com.community.manager.vo.PageResultVo;

import java.util.List;

/**
 * @Author:王喜
 * @Description :项目成果信息Service
 * @Date: 2017/11/14 0014 17:27
 */
public interface AchievementService {
    /**
     *分页展示出所有的项目成果信息
     *
     * @param page
     * @return
     */
    PageResultVo listAllAchievementByPage(Integer page);

    /**
     * 根据id修改项目成果的状态
     *
     * @param achievementId
     * @param showFlag
     * @return
     */
    Boolean updateAchievementById(Long achievementId,Integer showFlag);

    /**
     * 根据id删除项目成果
     *
     * @param achievementId
     * @return
     */
    Boolean removeAchievementById(Long achievementId);

    /**
     * 增加项目成果信息
     *
     * @param achievement
     * @return
     */
    Boolean insertAchievement(Achievement achievement);

    /**
     * 模糊搜索项目成果信息
     *
     * @param searchKeywords
     * @param searchParam
     * @param page
     * @return
     */
    PageResultVo listAchievementWithSearch(String searchKeywords, String searchParam, Integer page);

    /**
     * 根据id查询项目成果介绍
     * @param achievement_id
     * @return
     */
    Achievement getAchievementIntroductionById(Long achievement_id);

    /**
     * 根据id查询项目成果信息
     *
     * @param achievementId
     * @return
     */
    AchievementMemberVo getAchievementById(Long achievementId);

    /**
     * 根据id更新项目信息
     *
     * @param achievementId
     * @param achievement
     * @return
     */
    Boolean updateAchievementByAchievementId(Long achievementId, Achievement achievement);

    /**
     * 查询所有的项目成果信息
     *
     * @return
     */
    List<AchievementMemberVo> listAllAchievement();
}
