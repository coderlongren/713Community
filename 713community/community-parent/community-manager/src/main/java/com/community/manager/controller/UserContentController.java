package com.community.manager.controller;

import com.community.manager.entity.UserContent;
import com.community.manager.service.UserContentService;
import com.community.manager.vo.UserContentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:王喜
 * @Description :用户内容控制层
 * @Date: 2017/11/27 0027 19:32
 */
@Controller
@RequestMapping("/userContent")
public class UserContentController {

    //定义日志~
    public static final Logger LOGGER = LoggerFactory.getLogger(UserContentController.class);
    //注入Service对象
    @Autowired
    private UserContentService userContentService;

    /**
     * 通过用户id查询用户内容
     * @param userId
     * @return
     */
    @GetMapping("{userId}")
    public ResponseEntity<UserContent> findUserContentByUserId(@PathVariable("userId") Long userId) {
        boolean server_error_flag = false;
        //定义对象
        UserContentVo userContentVo = null;
        try {
            userContentVo = userContentService.getUserContentByUserId(userId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("根据用户id查询用户失败！程序出错！--------------------------> methodName : UserContentController.findUserContentByUserId");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == userContentVo) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

//        [{"name":"百度","post":"架构师","time":"342423","info":"dsfsd"},{"name":"火狐","post":"经理","time":"342423","info":"dsfsd"}]

        //200
        return ResponseEntity.ok(userContentVo.getUserContent());
    }
}
