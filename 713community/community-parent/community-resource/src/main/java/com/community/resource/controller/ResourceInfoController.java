package com.community.resource.controller;

import com.community.resource.service.ResourceInfoService;
import com.community.resource.vo.ResourceInfoLZVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @Author:王喜
 * @Description :资源详情Controller层
 * @Date: 2017/12/25 0025 9:24
 */
@Controller
@RequestMapping("/resource/info")
public class ResourceInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceInfoController.class);

    @Autowired
    private ResourceInfoService resourceInfoService;

    /**
     * 根据资源id查询资源详情
     * @param resourceId
     * @return
     */
    @GetMapping("{resourceId}")
    public ResponseEntity<ResourceInfoLZVo> viewResourcesTopInfoLZById(@PathVariable("resourceId") Long resourceId) {
        boolean server_error_flag = false;
        ResourceInfoLZVo resourceInfoLZVo = null;

        try {
            resourceInfoLZVo = resourceInfoService.viewLZAndTopInfoByResourceId(resourceId);

        } catch (IOException e) {
            server_error_flag = true;
            LOGGER.error("访问资源详情楼主信息出错！methodName : ResourceInfoController.viewResourcesTopInfoLZById, exception = {}", e);
        }

        if (server_error_flag) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == resourceInfoLZVo) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(resourceInfoLZVo);
    }
}
