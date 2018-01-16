package com.community.manager.api;

import com.community.manager.entity.Image;
import com.community.manager.entity.RecruitType;
import com.community.manager.service.ImageService;
import com.community.manager.service.RecruitTypeService;
import com.community.manager.vo.RecruitAndImageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author : tingting
 * @Description : 加入我们首面-对外接口服务
 * @Date : 2017/11/6 21:19
 */
@RequestMapping("api/recruit")
@Controller
public class RecruitAndImageApiController {

    @Autowired
    private RecruitTypeService recruitTypeService;

    @Autowired
    private ImageService imageService;

    public static final Logger LOGGER = LoggerFactory.getLogger(RecruitAndImageApiController.class);

    /**
     * 访问加入我们首页
     *
     * @param typeIds
     * @return
     */
    @GetMapping
    public ResponseEntity<RecruitAndImageVo> viewRecruitType(@RequestParam("typeIds") String typeIds) {

        boolean server_error_flag = false;

        List<Image> recruitTypeImgs = null;

        List<RecruitType> recruitTypes = null;

        try {
            recruitTypeImgs = imageService.listImageByTypeIds(typeIds);
            recruitTypes = recruitTypeService.listAllRecruitType();
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("访问加入我们首面出错！-------> methodName : RecruitInfoApiController.viewRecruitType");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (recruitTypeImgs == null || recruitTypes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        RecruitAndImageVo recruitAndImageVo = new RecruitAndImageVo(recruitTypeImgs, recruitTypes);

        return ResponseEntity.ok(recruitAndImageVo);
    }
}
