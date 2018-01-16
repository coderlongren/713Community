package com.community.manager.controller;

import com.community.manager.entity.Achievement;
import com.community.manager.service.AchievementService;
import com.community.manager.vo.AchievementMemberVo;
import com.community.manager.vo.PageResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:王喜
 * @Description :项目成果展示控制层
 *
 * @Date: 2017/11/14 0014 17:28
 */
@Controller
@RequestMapping("/achievement")
public class AchievementController {
    //定义日志~
    public static final Logger LOGGER = LoggerFactory.getLogger(AchievementController.class);
    //注入Service对象
    @Autowired
    private AchievementService achievementService;

    /**
     * 分页查询项目成果信息
     *
     * @param page  下一页
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewAchievementPage(@PathVariable("next") Integer page){
        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = achievementService.listAllAchievementByPage(page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询项目成果信息失败！程序出错！--------------------------> methodName : AchievementController.viewAchievementPage");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == pageResultVo || null == pageResultVo.getRows()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(pageResultVo);
    }

    /**
     * 根据id更改项目成果状态（是否上下架）
     *
     * @param achievementId
     * @param showFlag  项目成果是否上下架的状态标志
     * @return
     */
    @PutMapping("{achievementId}")
    public ResponseEntity<Void> updateAchievement(@PathVariable("achievementId") Long achievementId,Integer showFlag){
        boolean server_error_flag = false;

        boolean result = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新项目成果状态，AchievementId = {}",achievementId);
            }

            result = achievementService.updateAchievementById(achievementId,showFlag);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新项目成果状态成功，AchievementId = {}",achievementId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("更新项目成果状态失败！程序出错！--------------------------> methodName : AchievementController.updateAchievement");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 根据id删除项目成果信息
     *
     * @param achievementId
     * @return
     */
    @DeleteMapping("{achievementId}")
    public ResponseEntity<Void> removeAchievementById(@PathVariable ("achievementId") Long achievementId){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除项目成果，AchievementId = {}",achievementId);
            }

            result = achievementService.removeAchievementById(achievementId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除项目成果成功，AchievementId = {}",achievementId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除删除项目成果失败！程序出错！----------------------------> methodName : AchievementController.removeAchievementById");

        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //500
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //200
        return ResponseEntity.ok(null);
    }

    /**
     * 增加项目成果信息
     *
     * @param achievement
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Void> addAchievement(Achievement achievement){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增项目成果信息，Achievement = {}", achievement);
            }

            result = achievementService.insertAchievement(achievement);

            //在改变之后也需要打印日志信息
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增项目成果信息成功，Achievement = {}", achievement);
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOGGER.error("新增项目成果信息失败，程序出错！-------------------------> methodName : AchievementController.addAchievement");
        }

        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 模糊搜索项目成果信息
     * @param page
     * @param searchParam
     * @param searchKeywords
     * @return
     */
    @GetMapping("pages/{next}")
    public ResponseEntity<PageResultVo> searchAchievementByParamsAndKeywords(@PathVariable("next") Integer page,
                                                                          @RequestParam("searchParam") String searchParam,
                                                                          @RequestParam("searchKeywords") String searchKeywords) {
        boolean server_error_flag = false;
        PageResultVo pageResult = null;
        try {
            pageResult = achievementService.listAchievementWithSearch(searchKeywords,searchParam,page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("项目成果信息模糊搜索程序出错 ---------------> methodName : AchievementController.searchAchievementByParamsAndKeywords");
        }

        //404
        if (null == pageResult || pageResult.getRows().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(pageResult);
    }

    /**
     * 根据资源id查询项目成果介绍
     *
     * @param achievement_id 项目id
     * @return
     */
    @GetMapping("introduction/{achievement_id}")
    public ResponseEntity<Achievement> getIntroductionById(@PathVariable ("achievement_id") Long achievement_id) {
        boolean server_error_flag = false;
        //定义对象
        Achievement achievement=null;
        try {
            achievement = achievementService.getAchievementIntroductionById(achievement_id);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询项目成果信息失败！程序出错！--------------------------> methodName : AchievementController.getIntroductionById");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == achievement) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(achievement);
    }
    /**
     * 根据id查询项目成果信息
     *
     * @param achievementId
     * @return
     */
    @GetMapping("{achievementId}")
    public ResponseEntity<AchievementMemberVo> viewAchievementById(@PathVariable("achievementId") Long achievementId) {

        boolean server_error_flag = false;

        AchievementMemberVo achievementMemberVo = null;
        try {
            achievementMemberVo = achievementService.getAchievementById(achievementId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("根据id查询项目成果信息，程序出错！--------------------------> methodName : AchievementController.viewAchievementById");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == achievementMemberVo) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(achievementMemberVo);
    }

    /**
     * 更新项目成果信息
     *
     * @param achievementId
     * @param achievement
     * @return
     */
    @PutMapping("/data/{achievementId}")
    public ResponseEntity<Void> updateAchievement(@PathVariable("achievementId") Long achievementId, Achievement achievement) {
        boolean server_error_flag = false;

        boolean result = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新项目成果信息，achievementId = {}, achievement = {}",achievementId, achievement);
            }

            result = achievementService.updateAchievementByAchievementId(achievementId, achievement);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新更新项目成果信息成功，userId = {}, user = {}",achievementId, achievement);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("更新更新项目成果信息失败！程序出错！--------------------------> methodName : AchievementController.updateAchievement");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
