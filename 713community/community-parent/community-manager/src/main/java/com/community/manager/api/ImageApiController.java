package com.community.manager.api;

import com.community.manager.entity.Image;
import com.community.manager.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: xian
 * @Description:对外接口服务
 * @Date:create in 2017/11/16 10:38
 */
@RequestMapping("api/image")
@Controller
public class ImageApiController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ImageApiController.class);

    @Autowired
    private ImageService imageService;

    /**
     * 根据图片类型id集合查询图片
     *
     * @param typeIds
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Image>> listImageByTypeIds(@RequestParam("typeIds") String typeIds) {
        boolean server_error_flag = false;
        List<Image> images = null;

        try {
            images = imageService.listImageByTypeIds(typeIds);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("根据图片类型id集合查询出错！ methodName : ImageApiController.listImageByTypeIds");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == images) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(images);
    }
}
