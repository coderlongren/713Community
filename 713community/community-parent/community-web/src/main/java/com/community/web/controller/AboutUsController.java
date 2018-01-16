package com.community.web.controller;

import com.community.web.service.AboutUsService;
import com.community.web.vo.AboutUsVo;
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
 * @Description :关于我们Controller
 * @Date: 2017/11/26 0026 21:17
 */
@Controller
@RequestMapping("rest/about_us")
public class AboutUsController {

    @Autowired
    private AboutUsService aboutUsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AboutUsController.class);

    /**
     * 访问关于我们页面,获得数据
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<AboutUsVo> viewAboutUs() {
        boolean server_error_flag = false;
        AboutUsVo aboutUsVo = null;

        try {
            aboutUsVo = aboutUsService.getAboutUsInfo();
        } catch (IOException e) {
            server_error_flag = true;
            LOGGER.error("访问关于我们页面,获得数据出错！methodName : AboutUsController.viewAboutUs, exception = {}", e);
        }

        if (server_error_flag) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == aboutUsVo) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(aboutUsVo);
    }
}
