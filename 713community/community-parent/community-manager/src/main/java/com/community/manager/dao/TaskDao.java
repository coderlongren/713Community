package com.community.manager.dao;

import com.community.manager.entity.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:王喜
 * @Description :任务信息dao
 * @Date: 2017/11/16 0016 20:26
 */
public interface TaskDao extends BaseDao<Task>{
    /**
     * 根据搜索条件对项目成果信息进行模糊搜索
     *
     * @param searchParam
     * @param searchKeywords
     * @return
     */
    List<Task> listTaskWithSearch(@Param("searchParam") String searchParam,
                                                @Param("searchKeywords") String searchKeywords);
}
