package com.community.manager.controller;

import com.community.manager.entity.ResourceType;
import com.community.manager.service.ResourceTypeService;
import com.community.manager.vo.PageResultVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:王喜
 * @Description : 资源类别相关：控制层
 * @Date: 2017/11/2 0002 21:42
 */
@Controller
@RequestMapping("/resource/type")
public class ResourceTypeController {
    public static final Logger LOOGER = LoggerFactory.getLogger(ResourceTypeController.class);
    //注入Service对象
    @Autowired
    private ResourceTypeService resourceTypeService;


    /**
     * 分页查询资源类别
     *
     * @param page
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewAllResourceTypeByPage(@PathVariable("next") Integer page){
        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = resourceTypeService.listAllResourceTypeByPage(page);
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("分页查询资源类别失败！程序出错！--------------------------> methodName : ResourceTypeController.viewAllResourceTypeByPage");
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
     * 根据id查询资源类型
     *
     * @param resourceTypeId
     * @return
     */
    @GetMapping("{resourceTypeId}")
    public ResponseEntity<ResourceType> viewResourceTypeById(@PathVariable("resourceTypeId") Long resourceTypeId){
        boolean server_error_flag = false;
        //定义对象
        ResourceType resourceType=null;
        try {
            resourceType = resourceTypeService.getResourceTypeById(resourceTypeId);
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("查询资源类别失败！程序出错！--------------------------> methodName : ResourceTypeController.viewResourceTypeById");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == resourceType) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(resourceType);

    }

    /**
     * 更新资源类别
     *
     * @param resourceTypeId
     * @param resourceType
     * @return
     */
    @PutMapping("{resourceTypeId}")
    public ResponseEntity<Void> updateResourceType(@PathVariable("resourceTypeId") Long resourceTypeId,ResourceType resourceType){
        boolean server_error_flag = false;

        boolean result = false;
        try {
            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("更新资源类别，resourceTypeId = {}, resourceType = {}",resourceTypeId, resourceType);
            }

            result = resourceTypeService.updateResourceTypeById(resourceTypeId, resourceType);
            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("更新资源类别成功，resourceTypeId = {}, resourceType = {}",resourceTypeId, resourceType);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("更新资源类别失败！程序出错！--------------------------> methodName : ResourceTypeController.updateResourceType");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 根据id删除资源类别
     *
     * @param resourceTypeId
     * @return
     */
    @DeleteMapping("{resourceTypeId}")
    public ResponseEntity<Void> removeResourceTypeById(@PathVariable("resourceTypeId") Long resourceTypeId){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("删除资源类别，resourceTypeId = {}",resourceTypeId);
            }

            result = resourceTypeService.removeResourceTypeById(resourceTypeId);

            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("删除资源类别成功，resourceTypeId = {}",resourceTypeId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("删除资源类别失败！程序出错！----------------------------> methodName : ResourceTypeController.removeResourceTypeById");

        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //500
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //200
        return ResponseEntity.ok(null);
    }

    /**
     * 增加资源类别
     *
     * @param resourceType
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Void> addResourceType(ResourceType resourceType){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("新增资源类别，ResourceType = {}", resourceType);
            }

            result = resourceTypeService.insertResourceType(resourceType);

            //在改变之后也需要打印日志信息
            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("新增资源类别成功，ResourceType = {}", resourceType);
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOOGER.error("新增资源类别失败，程序出错！-------------------------> methodName : ResourceTypeController.addResourceType");
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
     * 模糊搜索分页查询
     *
     * @param page  下一页
     * @param searchParam  搜索条件
     * @return
     */
    @GetMapping("{searchParam}/page/{next}")
    public ResponseEntity<PageResultVo> searchResourceTypeByName(@PathVariable("searchParam") String searchParam,@PathVariable("next") Integer page){
        boolean server_error_flag = false;
        PageResultVo pageResult = null;
        try {
            pageResult = resourceTypeService.listResourceTypeWithSearch(searchParam,page);
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("资源类别模糊搜索程序出错 ---------------> methodName : ResourceTypeController.searchResourceTypeByName");
        }

        if (null == pageResult || pageResult.getRows().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(pageResult);
    }

}
