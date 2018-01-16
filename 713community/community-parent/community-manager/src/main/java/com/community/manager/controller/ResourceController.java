package com.community.manager.controller;

import com.community.manager.entity.Resource;
import com.community.manager.entity.ResourceInfo;
import com.community.manager.service.ResourceInfoService;
import com.community.manager.service.ResourceService;
import com.community.manager.vo.AddResourceVo;
import com.community.manager.vo.PageResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:王喜
 * @Description :资源信息的Controller层
 * @Date: 2017/11/5 0005 16:10
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {
    //定义日志~
    public static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);
    //注入Service对象
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceInfoService resourceInfoService;

    /**
     * 分页查询资源信息
     *
     * @param page  下一页
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewResourcePage(@PathVariable("next") Integer page){
        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = resourceService.listAllResourceByPage(page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询用户失败！程序出错！--------------------------> methodName : ResourceController.viewResourcePage");
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
     * 根据id更改资源状态（是否上下架）
     *
     * @param resourceId
     * @param showFlag  资源是否上下架的状态标志
     * @return
     */
    @PutMapping("{resourceId}")
    public ResponseEntity<Void> updateResource(@PathVariable("resourceId") Long resourceId,Integer showFlag){
        boolean server_error_flag = false;

        boolean result = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新资源状态，resourceId = {}",resourceId);
            }

            result = resourceService.updateResourceById(resourceId,showFlag);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新更新资源状态成功，resourceId = {}",resourceId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("更新更新资源状态失败！程序出错！--------------------------> methodName : ResourceController.updateResource");
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
     * 根据id删除资源信息
     *
     * @param resourceId
     * @return
     */
    @DeleteMapping("{resourceId}")
    public ResponseEntity<Void> removeResourceById(@PathVariable ("resourceId") Long resourceId){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除资源，resourceTypeId = {}",resourceId);
            }

            result = resourceService.removeResourceById(resourceId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除资源成功，resourceTypeId = {}",resourceId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除资源失败！程序出错！----------------------------> methodName : ResourceController.removeResourceById");

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
     * 查询添加资源下拉框的信息
     *
     * @return
     */
    @GetMapping("/loadOptionInfo")
    public ResponseEntity<AddResourceVo> loadOptionInfo(){
        boolean server_error_flag = false;

        AddResourceVo addResourceVo = null;

        try {
            addResourceVo = resourceService.listAllAddResourceVo();
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询添加资源下拉框信息失败！程序出错！--------------------------> methodName : ResourceController.loadOptionInfo");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == addResourceVo) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(addResourceVo);
    }

    /**
     * 增加资源信息
     * @param resource
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Void> addResource(Resource resource){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增资源信息，Resource = {}", resource);
            }

            result = resourceService.insertResource(resource);

            //在改变之后也需要打印日志信息
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增资源信息成功，Resource = {}", resource);
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOGGER.error("新增资源信息失败，程序出错！-------------------------> methodName : ResourceController.addResource");
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
     * 根据条件模糊搜索资源信息
     * @param page 下一页
     * @param searchParam 搜索条件
     * @param searchKeywords 搜索关键字
     * @return
     */
    @GetMapping("pages/{next}")
    public ResponseEntity<PageResultVo> searchResourceByParamsAndKeywords(@PathVariable("next") Integer page,
                                                                          @RequestParam("searchParam") String searchParam,
                                                                          @RequestParam("searchKeywords") String searchKeywords) {
        boolean server_error_flag = false;
        PageResultVo pageResult = null;
        try {
            pageResult = resourceService.listResourceWithSearch(searchKeywords,searchParam,page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("资源信息模糊搜索程序出错 ---------------> methodName : ResourceController.searchResourceByParamsAndKeywords");
        }

        //404
        if (null == pageResult || pageResult.getRows().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(pageResult);
    }

    /**
     * 根据资源id查询资源内容
     *
     * @param contentId 资源id
     * @return
     */
    @GetMapping("content/{content_id}")
    public ResponseEntity<ResourceInfo> getResourceContentById(@PathVariable ("content_id") Long contentId) {
        boolean server_error_flag = false;
        //定义对象
        ResourceInfo resourceinfo=null;
        try {
            resourceinfo = resourceInfoService.getContentById(contentId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询资源内容失败！程序出错！--------------------------> methodName : ResourceController.getResourceContentById");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == resourceinfo) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(resourceinfo);
    }

}
