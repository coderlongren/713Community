package com.community.web.controller;

import com.community.web.bean.Image;
import com.community.web.service.IndexService;
import com.community.web.vo.IndexVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xian
 * @Description:首页控制器
 * @Date:create in 2017/11/16 9:50
 */
@RequestMapping("rest/index")
@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    private ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private IndexService indexService;

    /**
     * 访问首页，主要是显示显示图片
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<IndexVo> viewIndex() {
        boolean server_error_flag = false;
        IndexVo indexVo = null;

        try {
            indexVo = indexService.listImageByTypeIds();

        } catch (IOException e) {
            server_error_flag = true;
            LOGGER.error("访问首页，查询图片出错！methodName : IndexController.viewIndex, exception = {}", e);
        }

        if (server_error_flag) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == indexVo) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(indexVo);
    }

    /**
     * 首页的导航不再从上方法获取，单独请求获取
     *
     * @return
     */
    @GetMapping("nav")
    public ResponseEntity<String> getIndexNav(@RequestParam(value = "callback", required = false) String callback) {
        boolean server_error_flag = false;
        IndexVo indexVo = null;
        String json = null;
        try {
            indexVo = indexService.listIndexNav();
            json = MAPPER.writeValueAsString(indexVo);
            if (null == callback) {
                //json
                return ResponseEntity.ok(json);
            }
        } catch (IOException e) {
            server_error_flag = true;
            LOGGER.error("访问首页，查询图片出错！methodName : IndexController.getIndexNav, exception = {}", e);
        }

        if (server_error_flag) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == indexVo) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //jsonp
        return ResponseEntity.ok(callback + "(" + json + ");");
    }
}
