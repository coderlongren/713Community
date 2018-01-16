package com.community.manager.controller;

import com.community.manager.entity.RecruitType;
import com.community.manager.service.RecruitTypeService;
import com.community.manager.vo.PageResultVo;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.jdbc.Null;
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
 * @Description :
 * @Date : 2017/11/3 18:26
 */

@RequestMapping("recruit")
@Controller
public class RecruitTypeController {

    @Autowired
    private RecruitTypeService recruitTypeService;

    public static final Logger LOGGER = LoggerFactory.getLogger(RecruitTypeController.class);


    /**
     * 查询全部的招新类型
     *
     * @return
     */
    @GetMapping("type/all")
    public ResponseEntity<List<RecruitType>> viewAllRecruitType() {
        boolean server_error_flag = false;

        List<RecruitType> recruitTypes = null;

        try {
            recruitTypes = recruitTypeService.listAllRecruitType();
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("招新信息查询失败，程序出错！----------->methodName : RecruitTypeController.viewAllRecruitType");
        }
        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (recruitTypes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(recruitTypes);
    }


    /**
     * 新增招新信息
     *
     * @param recruitType
     * @return
     */
    @PostMapping("type")
    public ResponseEntity<Void> addRecruit(RecruitType recruitType) {

        boolean server_error_flag = false;

        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增招新信息------------> recruitType = {}", recruitType);
            }

            result = recruitTypeService.insertRecruit(recruitType);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增招新信息成功------------> recruitType = {}", recruitType);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("新增失败！程序报错！----------------> methodName : RecruitTypeController.addRecruit");
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
     * 分页查询招新信息
     *
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewRecruitTypeByPage(@PathVariable("next") Integer page,
                                                              @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = recruitTypeService.listAllRecruitByPage(page, rows);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("招新信息分页查询失败，程序出错！----------->methodName : RecruitTypeController.viewRecruitTypeByPage");
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
     * 更新招新信息
     *
     * @param typeId
     * @param recruitType
     * @return
     */
    @PutMapping("type/{typeId}")
    public ResponseEntity<Void> updateRecruitType(@PathVariable("typeId") Long typeId, RecruitType recruitType) {

        boolean server_error_flag = false;

        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新招新信息， typeId = {}， recruitType = {}", recruitType.getRecruitName());
            }

            result = recruitTypeService.updateRecruit(typeId, recruitType);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新招新信息成功， typeId = {}， recruitType = {}", recruitType.getRecruitName());
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("更新招新信息失败！程序出错！---------------->methodName：RecruitTypeController.updateRecruitType");
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
     * 根据Id查询招新信息
     *
     * @param typeId
     * @return
     */
    @GetMapping("type/{typeId}")
    public ResponseEntity<RecruitType> viewRecruitTypeById(@PathVariable("typeId") Long typeId) {

        boolean server_error_flag = false;

        RecruitType recruitType = null;

        try {
            recruitType = recruitTypeService.getRecruitTypeById(typeId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询招新信息失败，程序出错！---------------> methodName : RecruitTypeController.viewRecruitTypeById");
        }

        if (recruitType == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(recruitType);
    }

    /**
     * 根据Id删除招新信息
     *
     * @param typeId
     * @return
     */
    @DeleteMapping("type/{typeId}")
    public ResponseEntity<Void> removeRecruitTypeById(@PathVariable("typeId") Long typeId) {

        boolean server_error_flag = false;

        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除招新信息，typeId = {}", typeId);
            }

            result = recruitTypeService.removeRecruitTypeById(typeId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除招新信息成功，typeId = {}", typeId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除招新信息失败！程序出错！---------> methodName: RecruitTypeController.removeRecruitTypeById");
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
     * 根据招新信息模糊搜索类型
     *
     * @param recruitType
     * @param rows
     * @param page
     * @return
     */
    @GetMapping("{searchParam}/page/{next}")
    public ResponseEntity<PageResultVo> searchRecruitType(@PathVariable("searchParam") String recruitType,
                                                          @RequestParam("rows") Integer rows,
                                                          @PathVariable("next") Integer page) {
        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = recruitTypeService.listTypeByInformation(recruitType, rows, page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("搜索招新信息失败，程序出错！---------> methodName : RecruitTypeController.searchRecruitType");
        }

        if (pageResultVo == null || pageResultVo.getRows() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(pageResultVo);
    }
}




