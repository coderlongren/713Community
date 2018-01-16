package com.community.resource.controller;

import com.community.resource.entity.Resource;
import com.community.resource.service.ResourceListService;
import com.community.resource.vo.PageResultVo;
import com.community.resource.vo.ResourceListTopVo;
import com.community.resource.vo.ResourceUploadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:王喜
 * @Description :资源列表Controller
 * @Date: 2017/12/19 0019 18:20
 */
@Controller
@RequestMapping("/resource/list/")
public class ResourceListController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceListController.class);

    @Autowired
    private ResourceListService resourceListService;

    /**
     * 根据资源类型查询资源列表首部
     * @param resourceTypeId
     * @return
     */
    @GetMapping("{resourceTypeId}")
    public ResponseEntity<ResourceListTopVo> viewResourcesTopByTypeId(@PathVariable("resourceTypeId")  Long resourceTypeId) {
        boolean server_error_flag = false;
        ResourceListTopVo resourceListTopVo = null;

        try {
            resourceListTopVo = resourceListService.listResourceListTopInfoById(resourceTypeId);

        } catch (IOException e) {
            server_error_flag = true;
            LOGGER.error("访问资源列表页面出错！methodName : ResourceListController.viewResourcesTopByTypeId, exception = {}", e);
        }

        if (server_error_flag) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == resourceListTopVo) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(resourceListTopVo);
    }

    /**
     *  根据资源类别id分页查询资源列表信息
     * @param resourceTypeId
     * @param page
     * @return
     */
    @GetMapping("/{resourceTypeId}/page/{next}")
    public ResponseEntity<PageResultVo> viewResourcesByPage(@PathVariable("resourceTypeId") Long resourceTypeId, @PathVariable("next") Integer page) {
        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = resourceListService.listAllResourceByPage(resourceTypeId,page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("资源列表页面，分页查询资源列表信息失败！程序出错！--------------------------> methodName : ResourceListController.viewResourcesByPage");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == pageResultVo || null == pageResultVo.getRows()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(pageResultVo);
    }

    /**
     * 资源信息的上传
     *
     * @param file
     * @param content
     * @param title
     * @param author
     * @param resourceTypeId
     * @return
     */
    @PostMapping("/file")
    public ResponseEntity<Void> addResourceWithZipFile(@RequestParam("file") MultipartFile file,
                                                       @RequestParam("content") String content,
                                                       @RequestParam("title") String title,
                                                       @RequestParam("author") String author,
                                                       @RequestParam("resourceTypeId") Long resourceTypeId) {
        boolean server_error_flag = false;

        ResourceUploadVo resourceUploadVo = new ResourceUploadVo(title,author,content,resourceTypeId);

        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("上传文件信息，file = {}", file.getOriginalFilename());
            }

            result = resourceListService.uploadZipFile(file,resourceUploadVo);

            //在改变之后也需要打印日志信息
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("上传文件信息成功，file = {}", file.getOriginalFilename());
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOGGER.error("上传文件失败，程序出错！-------------------------> methodName : UserController.uploadResourceZipFiles");
        }

        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 上传图片，用于富文本编辑器的上传
     *
     * @param pic
     */
    @PostMapping("editor/upload")
    @ResponseBody
    public Map<String, String> uploadImageInEditor(@RequestParam("file") MultipartFile pic) {

        Map<String, String> map = new HashMap<>();
        String link = null;
        if (!pic.isEmpty()) {
            try {
                link =  resourceListService.uploadPicture(pic);
                map.put("link", link);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
