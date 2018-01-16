package com.community.resource.dao;

import com.community.resource.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:王喜
 * @Description : 资源列表的dao
 * @Date: 2017/12/19 0019 18:18
 */
public interface ResourceListDao {

    /**
     * 根据时间查询当天的该类别资源总数
     *
     * @param date
     * @param resourceTypeId
     * @return
     */
    Integer countResourceTotal(@Param("date") String date, @Param("resourceTypeId") Long resourceTypeId);

    /**
     * 根据每个资源类别得出资源的个数（根据总的资源个数来排名）
     * @return
     */
    List<Resource> countResourceByResourceType();

    /**
     * 分页查询资源信息
     *
     * @return
     */
    List<Resource> listAll(Long resourceTypeId);

    /**
     * 插入资源信息
     *
     * @param resource
     * @return
     */
    Integer insertResource(Resource resource);
}
