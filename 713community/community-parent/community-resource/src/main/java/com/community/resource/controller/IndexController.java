package com.community.resource.controller;

import com.community.resource.service.IndexService;
import com.community.resource.vo.IndexVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @Author:王喜
 * @Description :资源首页Controller层
 * @Date: 2017/12/19 0019 9:51
 */

@Controller
@RequestMapping("/resource/index")
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IndexService  indexService;

    /**
     * 访问资源共享首页
     * @return
     */
    @GetMapping()
    public ResponseEntity<IndexVo> viewResourceShareIndex() {
        boolean server_error_flag = false;
        IndexVo indexVo = null;

        try {
            indexVo = indexService.listResourceIndexInfo();

        } catch (IOException e) {
            server_error_flag = true;
            LOGGER.error("访问资源共享首页，查询出错！methodName : IndexController.viewResourceShareIndex, exception = {}", e);
        }

        if (server_error_flag) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == indexVo) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(indexVo);
    }
}
