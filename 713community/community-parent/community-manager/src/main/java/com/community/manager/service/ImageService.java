package com.community.manager.service;

import com.community.manager.entity.Image;
import com.community.manager.vo.ImageAndTypeVo;
import com.community.manager.vo.PageResultVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: xian
 * @Description:图片service接口
 * @Date:create in 2017/11/4 19:56
 */
public interface ImageService {

    /**
     * 上传图片
     *
     * @param pic
     * @param image
     * @return 图片的src路径
     * @throws IOException
     */
    String uploadPicture(MultipartFile pic, Image image) throws IOException;

    /**
     * 添加图片记录
     *
     * @param image
     * @return
     */
    Long insertImage(Image image);

    /**
     * 根据id查询图片数据
     *
     * @param imageIdOnUpload
     * @return
     */
    Image getImageById(Long imageIdOnUpload);

    /**
     * 分页查询图片数据
     *
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listAllByPage(Integer page, Integer rows);

    /**
     * 根据id查询图片数据，附带图片类型信息
     *
     * @param imageId
     * @return
     */
    ImageAndTypeVo getByIdWithType(Long imageId);

    /**
     * 更新图片信息
     *
     * @param image
     * @param imageId
     * @return
     */
    Boolean updateImage(Long imageId, Image image);

    /**
     * 根据id删除图片信息
     *
     * @param imageId
     * @return
     */
    Boolean removeImage(Long imageId);

    /**
     * 根据图片类型查询图片列表
     *
     * @param typeId
     * @return
     */
    List<Image> listImageByType(Long typeId);


    /**
     * 根据图片类型id集合查询图片列表
     *
     * @param typeIds
     * @return
     */
    List<Image> listImageByTypeIds(String typeIds);

    /**
     * 分页搜索查询
     *
     * @param page
     * @param rows
     * @param param
     * @param value
     * @return
     */
    PageResultVo listSearchImageByPage(Integer page, Integer rows, String param, String value);
}
