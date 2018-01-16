package com.community.manager.controller;

import com.community.manager.entity.Image;
import com.community.manager.service.ImageService;
import com.community.manager.vo.ImageAndTypeVo;
import com.community.manager.vo.PageResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xian
 * @Description:图片controller层
 * @Date:create in 2017/11/4 16:54
 */
@RequestMapping("image")
@Controller
public class ImageController {

    /*定义一个全局变量，用于存储在新增图片操作时，保存刚刚新增的图片记录id，以便于上传图片，仅用于新增操作*/
    private Long imageIdOnUpload;

    public static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    /**
     * 上传图片，用于富文本编辑器的上传
     *
     * @param pic
     */
    @PostMapping("editor/upload")
    @ResponseBody
    public Map<String, String> uploadImageInEditor(@RequestParam("file") MultipartFile pic, String postId) {

        Map<String, String> map = new HashMap<>();
        String link = null;
        if (pic != null) {
            try {
                link =  imageService.uploadPicture(pic, null);
                map.put("link", link);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 上传图片,新增用
     *
     * @param pic
     * @return
     */
    @PostMapping("upload")
    public ResponseEntity<Integer> uploadImage(@RequestParam("file") MultipartFile pic) {


        if (imageIdOnUpload == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Integer code = 0;
        try {

            Image image = imageService.getImageById(imageIdOnUpload);

            if (null == image) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("上传图片------------>picture = {}", pic.getOriginalFilename());
            }

            imageService.uploadPicture(pic, image);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("上传图片成功------------>picture = {}", pic.getOriginalFilename());
            }
        } catch (IOException e) {
            LOGGER.error("上传图片失败！程序出错！----------------------> methodName : ImageController.uploadImage");
            code = 2;
        }

        code = 1;

        //上传一张图片成功后将imageIdOnUpload置null
        imageIdOnUpload = null;

        return ResponseEntity.ok(code);
    }

    /**
     * 上传图片,更新用
     *
     * @param pic
     * @return
     */
    @PostMapping("upload/{imageId}")
    public ResponseEntity<Integer> uploadImageOnUpdate(@RequestParam("file") MultipartFile pic,
                                                       @PathVariable("imageId") Long imageId) {

        Integer code = 0;
        try {
            Image image = imageService.getImageById(imageId);

            if (null == image) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("上传图片------------>picture = {}", pic.getOriginalFilename());
            }

            imageService.uploadPicture(pic, image);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("上传图片成功------------>picture = {}", pic.getOriginalFilename());
            }
        } catch (IOException e) {
            LOGGER.error("上传图片失败！程序出错！----------------------> methodName : ImageController.uploadImage");
        }

        code = 1;

        return ResponseEntity.ok(code);
    }

    /**
     * 更新图片信息
     *
     * @param imageId
     * @param image
     * @return
     */
    @PutMapping("{imageId}")
    public ResponseEntity<Void> updateImage(@PathVariable("imageId") Long imageId, Image image) {
        boolean server_error_flag = false;

        boolean result = false;

        try {

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新图片------------>image = {}", image);
            }

            result = imageService.updateImage(imageId, image);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新图片成功------------>image = {}", image);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("更新图片失败！程序出错！----------------------> methodName : ImageController.updateImage");
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
     * 添加图片，该方法还没开始上传图片，先是创建一条图片记录
     *
     * @param image
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addImage(Image image) {
        boolean server_error_flag = false;

        Long imageId = null;

        try {

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("添加图片------------>image = {}", image);
            }

            imageId = imageService.insertImage(image);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("添加图片成功------------>image = {}", image);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("添加图片失败！程序出错！----------------------> methodName : ImageController.addImage");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if (imageId == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        //if imageId != null,给全局变量赋值
        imageIdOnUpload = imageId;

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 分页查询图片数据
     *
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewImageByPage(@PathVariable("next") Integer page,
                                                        @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        boolean server_error_flag = false;
        PageResultVo pageResultVo = null;

        try {
            pageResultVo = imageService.listAllByPage(page, rows);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询图片数据失败，程序出错！--------------------------> methodName : ImageController.viewImageByPage");
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
     * 根据id查询图片数据，附带图片类型信息
     *
     * @param imageId
     * @return
     */
    @GetMapping("{imageId}")
    public ResponseEntity<ImageAndTypeVo> viewImageWithType(@PathVariable("imageId") Long imageId) {
        boolean server_error_flag = false;
        ImageAndTypeVo imageAndTypeVo = null;

        try {
            imageAndTypeVo = imageService.getByIdWithType(imageId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询图片数据失败，程序出错！--------------------------> methodName : ImageController.viewImageWithType");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == imageAndTypeVo) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(imageAndTypeVo);
    }


    /**
     * 根据类型id查询图片列表
     *
     * @param typeId
     * @return
     */
    @GetMapping("group/type/{typeId}")
    public ResponseEntity<List<Image>> viewImagesByType(@PathVariable("typeId") Long typeId) {
        boolean server_error_flag = false;
        List<Image> images = null;

        try {
            images = imageService.listImageByType(typeId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询图片数据失败，程序出错！--------------------------> methodName : ImageController.viewImagesByType");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == images) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(images);
    }

    /**
     * 删除图片记录
     *
     * @return
     */
    @DeleteMapping("{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable("imageId") Long imageId) {
        boolean server_error_flag = false;

        boolean result = false;

        try {

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除图片------------>imageId = {}", imageId);
            }

            result = imageService.removeImage(imageId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除图片成功------------>imageId = {}", imageId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除图片失败！程序出错！----------------------> methodName : ImageController.deleteImage");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(null);
    }

    /**
     * 分页搜索查询
     *
     * @param page
     * @param param : 查询条件
     * @param value : 条件对应的值
     * @param rows
     * @return
     */
    @GetMapping("{param}/{value}/page/{next}")
    public ResponseEntity<PageResultVo> searchImageByPage(@PathVariable("next") Integer page,
                                                          @PathVariable("param") String param,
                                                          @PathVariable("value") String value,
                                                          @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        boolean server_error_flag = false;
        PageResultVo pageResultVo = null;

        try {
            pageResultVo = imageService.listSearchImageByPage(page, rows, param, value);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页搜索查询图片数据失败，程序出错！--------------------------> methodName : ImageController.viewImageByPage");
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
