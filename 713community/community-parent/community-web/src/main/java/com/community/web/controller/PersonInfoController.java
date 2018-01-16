package com.community.web.controller;

import com.community.web.service.PersonInfoService;
import com.community.web.vo.PersonInfoVo;
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
 * @Description :
 * @Date: 2017/11/28 0028 16:01
 */
@Controller
@RequestMapping("rest/person_info")
public class PersonInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonInfoController.class);

    @Autowired
    private PersonInfoService personInfoService;

    @GetMapping("{userId}")
    public ResponseEntity<PersonInfoVo> viewPersonInfo(@PathVariable("userId") Long userId ) {
        boolean server_error_flag = false;
        PersonInfoVo personInfo = null;

        try {
            personInfo = personInfoService.getPersonInfoInfo(userId);
        } catch (IOException e) {
            server_error_flag = true;
            LOGGER.error("访问个人介绍页面,获得数据出错！methodName : PersonInfoController.viewPersonInfo, exception = {}", e);
        }

        if (server_error_flag) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == personInfo) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(personInfo);
    }

}
