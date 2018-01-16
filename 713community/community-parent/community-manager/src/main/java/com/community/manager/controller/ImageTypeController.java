package com.community.manager.controller;

import com.community.manager.entity.ImageType;
import com.community.manager.service.ImageTypeService;
import com.community.manager.vo.PageResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: xian
 * @Description:图片类型控制层
 * @Date:create in 2017/11/3 16:52
 */
@RequestMapping("image/type")
@Controller
public class ImageTypeController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ImageTypeController.class);

    @Autowired
    private ImageTypeService imageTypeService;


    /**
     * 根据id查询图片类别数据
     *
     * @param imgTypeId
     * @return
     */
    @GetMapping("{imgTypeId}")
    public ResponseEntity<ImageType> viewImageTypeById(@PathVariable("imgTypeId") Long imgTypeId) {
        boolean server_error_flag = false;

        ImageType imageType = null;

        try {
            imageType = imageTypeService.getImageTypeById(imgTypeId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询图片类别失败！程序出错！----------------------> methodName : ImageTypeController.viewImageTypeById");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == imageType) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(imageType);
    }

    /**
     * 查询所有的图片类别
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ImageType>> viewAllImageType() {
        boolean server_error_flag = false;

        List<ImageType> imageTypes = null;

        try {
            imageTypes = imageTypeService.listAllImageType();
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询图片类别失败！程序出错！----------------------> methodName : ImageTypeController.viewImageTypeById");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == imageTypes) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(imageTypes);
    }

    /**
     * 分页查询图片类型列表数据
     *
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewImageTypeByPage(@PathVariable("next") Integer page,
                                                            @RequestParam(value = "rows", defaultValue = "10") Integer rows) {

        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = imageTypeService.listAllByPage(page, rows);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询图片类别失败！程序出错！----------------------> methodName : ImageTypeController.viewImageTypeByPage");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == pageResultVo || null == pageResultVo.getRows()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(pageResultVo);
    }

    /**
     * 添加图片类型
     *
     * @param imageType
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addImageType(ImageType imageType) {

        boolean server_error_flag = false;

        boolean result = false;

        try {

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("添加图片类型------------>imageType = {}", imageType);
            }

            result = imageTypeService.insertImageType(imageType);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("添加图片类型成功------------>imageType = {}", imageType);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("添加图片类型失败！程序出错！----------------------> methodName : ImageTypeController.addImageType");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * 更新图片类型
     *
     * @param imgTypeId
     * @param imageType
     * @return
     */
    @PutMapping("{imgTypeId}")
    public ResponseEntity<Void> updateImageType(@PathVariable("imgTypeId") Long imgTypeId, ImageType imageType) {
        boolean server_error_flag = false;

        boolean result = false;

        try {

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新图片类型------------>imgTypeId = {},imageType = {}",imgTypeId, imageType);
            }

            result = imageTypeService.updateImageType(imageType, imgTypeId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新图片类型成功------------>imgTypeId = {},imageType = {}",imgTypeId, imageType);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("更新图片类型失败！程序出错！----------------------> methodName : ImageTypeController.updateImageType");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


    /**
     * 删除图片类型
     *
     * @param imgTypeId
     * @return
     */
    @DeleteMapping("{imgTypeId}")
    public ResponseEntity<Void> deleteImageType(@PathVariable("imgTypeId") Long imgTypeId) {
        boolean server_error_flag = false;

        boolean result = false;

        try {

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除图片类型------------>imgTypeId = {}",imgTypeId);
            }

            result = imageTypeService.removeImageType(imgTypeId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除图片类型成功------------>imgTypeId = {}",imgTypeId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除图片类型失败！程序出错！----------------------> methodName : ImageTypeController.deleteImageType");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * 分页搜索查询图片类别数据
     *
     * @param page
     * @param typeName
     * @param rows
     * @return
     */
    @GetMapping("{searchParam}/page/{next}")
    public ResponseEntity<PageResultVo> searchImageTypeByPage(@PathVariable("next") Integer page,
                                                              @PathVariable("searchParam") String typeName,
                                                              @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = imageTypeService.listSearchImageTypeByPage(page, rows, typeName);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页搜索查询图片类别失败！程序出错！----------------------> methodName : ImageTypeController.searchImageTypeByPage");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == pageResultVo || null == pageResultVo.getRows()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(pageResultVo);
    }

}
