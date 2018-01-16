package com.community.manager.dao;

import com.community.manager.entity.ImageType;

import java.util.List;

/**
 * @Author: xian
 * @Description:图片类型dao接口
 * @Date:create in 2017/11/3 18:25
 */
public interface ImageTypeDao extends BaseDao<ImageType> {

    /**
     * 搜索查询图片类别数据
     *
     * @param value
     * @return
     */
    List<ImageType> lisImageTypetBySearch(String value);
}
