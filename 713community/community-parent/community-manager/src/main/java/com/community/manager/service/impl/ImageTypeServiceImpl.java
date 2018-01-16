package com.community.manager.service.impl;

import com.community.manager.dao.ImageTypeDao;
import com.community.manager.entity.ImageType;
import com.community.manager.service.ImageTypeService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: xian
 * @Description:图片类型service实现类
 * @Date:create in 2017/11/3 16:53
 */
@Service
public class ImageTypeServiceImpl implements ImageTypeService {

    @Autowired
    private ImageTypeDao imageTypeDao;

    /**
     * 添加图片类型
     *
     * @param imageType
     * @return
     */
    @Override
    public Boolean insertImageType(ImageType imageType) {

        imageType.setCreateTime(new Date());
        imageType.setUpdateTime(new Date());

        return imageTypeDao.insert(imageType) == 1;
    }

    /**
     * 分页查询图片类型列表数据
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listAllByPage(Integer page, Integer rows) {

        if (null == page || page <= 0) {
            page = 1;
        }
        if (null == rows) {
            rows = 10;
        }

        PageHelper.startPage(page, rows);
        List<ImageType> list = imageTypeDao.listAll();
        PageInfo<ImageType> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 根据id查询图片类别数据
     *
     * @param imgTypeId
     * @return
     */
    @Override
    public ImageType getImageTypeById(Long imgTypeId) {
        return imageTypeDao.getById(imgTypeId);
    }

    /**
     * 根据id更新图片类型
     *
     * @param imageType
     * @param imgTypeId
     * @return
     */
    @Override
    public Boolean updateImageType(ImageType imageType, Long imgTypeId) {
        imageType.setId(imgTypeId);
        imageType.setUpdateTime(new Date());
        return imageTypeDao.update(imageType) == 1;
    }

    /**
     * 根据id删除图片类型
     *
     * @param imgTypeId
     * @return
     */
    @Override
    public Boolean removeImageType(Long imgTypeId) {
        return imageTypeDao.deleteById(imgTypeId) == 1;
    }

    /**
     * 查询所有的图片类型数据
     *
     * @return
     */
    @Override
    public List<ImageType> listAllImageType() {
        return imageTypeDao.listAll();
    }

    /**
     * 分页搜索查询图片类别数据
     *
     * @param page
     * @param rows
     * @param typeName
     * @return
     */
    @Override
    public PageResultVo listSearchImageTypeByPage(Integer page, Integer rows, String typeName) {
        if (null == page || page < 1) {
            page = 1;
        }
        String value = new StringBuilder("%").append(typeName).append("%").toString();
        PageHelper.startPage(page, rows);
        List<ImageType> list = imageTypeDao.lisImageTypetBySearch(value);
        PageInfo<ImageType> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }
}
