package com.community.manager.dao;

import com.community.manager.entity.Achievement;
import com.community.manager.vo.AchievementMemberVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:王喜
 * @Description :项目成果信息Dao
 * @Date: 2017/11/14 0014 17:26
 */
public interface AchievementDao extends BaseDao<Achievement>{
    /**
     * 根据id更新项目成果的状态消息
     *
     * @param achievementId
     * @param showFlag
     * @return
     */
    Integer updateAchievementById(@Param("achievementId") Long achievementId, @Param("showFlag") Integer showFlag);


    /**
     * 根据搜索条件对项目成果信息进行模糊搜索
     *
     * @param searchParam
     * @param searchKeywords
     * @return
     */
    List<Achievement> listAchievementWithSearch(@Param("searchParam") String searchParam,
                                          @Param("searchKeywords") String searchKeywords);
}
