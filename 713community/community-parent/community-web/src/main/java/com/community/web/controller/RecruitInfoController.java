package com.community.web.controller;


import com.community.web.bean.RecruitInfo;
import com.community.web.service.RecruitInfoService;
import com.community.web.vo.RecruitInfoVo;
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
 * @Author : tingting
 * @Description : 加入我们详情页控制器
 * @Date : 2017/11/28 19:56
 */
public class RecruitInfoController  {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruitInfoController.class);

    @Autowired
    private RecruitInfoService recruitInfoService;

    @GetMapping("{infoId}/info")
    public ResponseEntity<RecruitInfoVo> viewRecruitInfo(@PathVariable("infoId") Long infoId) throws IOException {
        boolean server_error_flag = false;

        RecruitInfo recruitInfo = null;
        RecruitInfoVo recruitInfoVo = new RecruitInfoVo();

        try {
            recruitInfo = recruitInfoService.getRecruitInfoVo(infoId);
            recruitInfoVo.setRecruitInfo(recruitInfo);
        } catch (IOException e) {
            server_error_flag = true;
            LOGGER.error("获取加入我们详情页招新信息数据失败！-----> methodName : RecruitInfoController.viewRecruitInfo");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (recruitInfoVo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(recruitInfoVo);
    }
}
