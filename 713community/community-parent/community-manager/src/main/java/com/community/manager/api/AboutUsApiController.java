package com.community.manager.api;

import com.community.manager.entity.Community;
import com.community.manager.service.AchievementService;
import com.community.manager.service.CommunityService;
import com.community.manager.vo.AchievementMemberVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author:王喜
 * @Description :查询出Community和Achievement 接口
 * @Date: 2017/11/27 0027 14:22
 */
@Controller
@RequestMapping("api/")
public class AboutUsApiController {

    public static final Logger LOGGER = LoggerFactory.getLogger(AboutUsApiController.class);

    @Autowired
    private CommunityService communityService;

    @Autowired
    private AchievementService achievementService;

    /**
     * 查询社区信息
     * @return
     */
    @GetMapping("community")
    public ResponseEntity<Community> listCommunity() {
        boolean server_error_flag = false;
        Community community = null;

        try {
            community = communityService.listAllCommunity();
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("社区信息查询出错！ methodName : AboutUsApiController.listCommunity");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == community) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(community);
    }

    /**
     * 查询项目成果信息
     *
     * @return
     */
    @GetMapping("achievement")
    public ResponseEntity<List<AchievementMemberVo>> listAchievement() {
        boolean server_error_flag = false;
        List<AchievementMemberVo> achievementMemberVos = null;

        try {
            achievementMemberVos = achievementService.listAllAchievement();
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("项目成果信息查询出错！ methodName : AboutUsApiController.listAchievement");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == achievementMemberVos) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(achievementMemberVos);
    }
}
