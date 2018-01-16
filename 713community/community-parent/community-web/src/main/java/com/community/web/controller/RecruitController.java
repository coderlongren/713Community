package com.community.web.controller;

import com.community.web.bean.RecruitInfo;
import com.community.web.service.RecruitService;
import com.community.web.vo.RecruitAndImageVo;
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
 * @Description : 加入我们首页控制器
 * @Date : 2017/11/27 11:11
 */
@RequestMapping("rest/recruit")
@Controller
public class RecruitController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruitController.class);

    @Autowired
    private RecruitService recruitService;

    /**
     * 获取招新类型
     *
     * @return
     */
    @GetMapping("type")
    public ResponseEntity<RecruitAndImageVo> viewRecruitType() {
        boolean server_error_flag = false;

        RecruitAndImageVo recruitAndImageVo = null;

        try {
            recruitAndImageVo = recruitService.getImagesAndRecruitTypesVo();
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("访问加入我们首面出错！-------> methodName : RecruitInfoApiController.viewRecruitType");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (recruitAndImageVo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(recruitAndImageVo);
    }

    /**
     * 获取招新内容详情
     *
     * @param infoId
     * @return
     * @throws IOException
     */
    @GetMapping("{infoId}/info")
    public ResponseEntity<RecruitInfoVo> viewRecruitInfo(@PathVariable("infoId") Long infoId) throws IOException {
        boolean server_error_flag = false;

        RecruitInfo recruitInfo = null;
        RecruitInfoVo recruitInfoVo = new RecruitInfoVo();

        try {
            recruitInfo = recruitService.getRecruitInfoVo(infoId);
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
