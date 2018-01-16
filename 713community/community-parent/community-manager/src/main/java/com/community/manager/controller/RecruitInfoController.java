package com.community.manager.controller;

import com.community.manager.entity.RecruitInfo;
import com.community.manager.service.RecruitInfoService;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.RecruitAndTypeVo;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/6 21:19
 */

@RequestMapping("recruit")
@Controller
public class RecruitInfoController {

    @Autowired
    private RecruitInfoService recruitInfoService;

    public static final Logger LOGGER = LoggerFactory.getLogger(RecruitInfoController.class);

    /**
     * 新增招新标准详情信息
     *
     * @param recruitInfo
     * @return
     */
    @PostMapping("info")
    public ResponseEntity<Void> addRecruitInfo(RecruitInfo recruitInfo) {

        boolean server_error_flag = false;

        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增招新标准详情信息-------> recruitInfo = {}", recruitInfo);
            }

            result = recruitInfoService.insertRecruitInfo(recruitInfo);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增招新标准详情信息成功-------> recruitInfo = {}", recruitInfo);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("新增招新标准详情信息失败！程序出错！-------> methodName ： RecruitInfoController.addRecruitInfo");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 更新招新标准详情信息
     *
     * @param typeId
     * @param recruitInfo
     * @return
     */
    @PutMapping("info/{typeId}")
    public ResponseEntity<Void> updateRecruitInfo(@PathVariable("typeId") Long typeId, RecruitInfo recruitInfo) {
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新招新标准详情信息, typeId = {}, recruitInfo = {}", typeId, recruitInfo);
            }

            result = recruitInfoService.updateRecruitInfo(typeId, recruitInfo);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新招新标准详情信息成功， typeId = {}， recruitType = {}", typeId, recruitInfo);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("更新招新标准详情信息失败！程序出错！---------------->methodName：RecruitInfoController.updateRecruitInfo");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 根据id删除招新标准详情信息
     *
     * @param typeId
     * @return
     */
    @DeleteMapping("info/{typeId}")
    public ResponseEntity<Void> removeRecruitInfoById(@PathVariable("typeId") Long typeId) {
        boolean server_error_flag = false;

        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除招新标准详情信息，typeId = {}", typeId);
            }

            result = recruitInfoService.deleteRecruitInfoById(typeId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除招新标准详情信息，typeId = {}", typeId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除招新标准详情信息失败！程序出错！---------> methodName: RecruitInfoController.removeRecruitInfoById");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(null);
    }

    /**
     * 分页查询招新标准详情信息
     *
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("info/page/{next}")
    public ResponseEntity<PageResultVo> viewRecruitInfoByPage(@PathVariable("next") Integer page,
                                                              @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = recruitInfoService.listAllRecruitInfoByPage(page, rows);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("招新详情信息分页查询失败，程序出错！----------->methodName : RecruitInfoController.viewRecruitInfoByPage");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (pageResultVo == null || pageResultVo.getRows() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(pageResultVo);
    }

    /**
     * 根据id查询招新标准详情信息
     *
     * @param infoId
     * @return
     */
    @GetMapping("info/{infoId}")
    public ResponseEntity<RecruitAndTypeVo> viewRecruitInfoById(@PathVariable("infoId") Long infoId) {

        boolean server_error_flag = false;

        RecruitAndTypeVo recruitAndTypeVo = null;

        try {
            recruitAndTypeVo = recruitInfoService.getRecruitInfoById(infoId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询招新标准详情信息失败，程序出错！---------------> methodName : RecruitInfoController.viewRecruitInfoById");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (recruitAndTypeVo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(recruitAndTypeVo);
    }


}
