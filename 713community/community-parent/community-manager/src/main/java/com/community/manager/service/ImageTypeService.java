package com.community.manager.service;

import com.community.manager.entity.ImageType;
import com.community.manager.vo.PageResultVo;

import java.util.List;

/**
 * @Author: xian
 * @Description:图片类型service接口
 * @Date:create in 2017/11/3 16:53
 */
public interface ImageTypeService {

    /**
     * 添加图片类型
     *
     * @param imageType
     * @return
     */
    Boolean insertImageType(ImageType imageType);

    /**
     * 分页查询图片类型列表数据
     *
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listAllByPage(Integer page, Integer rows);

    /**
     * 根据id查询图片类别数据
     *
     * @param imgTypeId
     * @return
     */
    ImageType getImageTypeById(Long imgTypeId);

    /**
     * 根据id更新图片类型
     *
     * @param imageType
     * @param imgTypeId
     * @return
     */
    Boolean updateImageType(ImageType imageType, Long imgTypeId);

    /**
     * 根据id删除图片类型
     *
     * @param imgTypeId
     * @return
     */
    Boolean removeImageType(Long imgTypeId);

    /**
     * 查询所有的图片类型数据
     *
     * @return
     */
    List<ImageType> listAllImageType();

    /**
     * 分页搜索查询图片类别数据
     *
     * @param page
     * @param rows
     * @param typeName
     * @return
     */
    PageResultVo listSearchImageTypeByPage(Integer page, Integer rows, String typeName);
}
