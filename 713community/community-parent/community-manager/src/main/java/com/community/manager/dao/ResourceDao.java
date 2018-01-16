package com.community.manager.dao;

import com.community.manager.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:王喜
 * @Description : 资源信息Dao
 * @Date: 2017/11/5 0005 16:06
 */
public interface ResourceDao extends BaseDao<Resource>{
    /**
     * 根据id更新资源的状态消息
     *
     * @param resourceId
     * @param showFlag
     * @return
     */
    Integer updateResourceById(@Param("resourceId") Long resourceId, @Param("showFlag") Integer showFlag);

    /**
     * 根据搜索条件对资源信息进行模糊搜索
     *
     * @param searchParam
     * @param searchKeywords
     * @return
     */
    List<Resource> listResourceWithSearch(@Param("searchParam") String searchParam,
                                          @Param("searchKeywords") String searchKeywords);
}
