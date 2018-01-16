package com.community.manager.dao;

import com.community.manager.entity.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/11/4 21:24
 */
public interface ImageDao extends BaseDao<Image> {

    /**
     * 根据图片类型查询图片列表
     *
     * @param typeId
     * @return
     */
    List<Image> listImageByType(Long typeId);

    /**
     * 搜索
     *
     * @param param
     * @param val
     * @return
     */
    List<Image> listBySearch(@Param("param") String param, @Param("value") String val);

    /**
     * 根据图片类型id集合查询图片列表
     *
     * @param listId
     * @return
     */
    List<Image> listByTypeIds(List<Long> listId);
}
