package com.community.manager.api;

import com.community.manager.controller.UserController;
import com.community.manager.entity.User;
import com.community.manager.service.UserService;
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
 * @Description :
 * @Date: 2017/11/28 0028 16:36
 */
@Controller
@RequestMapping("api/user")
public class PersonINfoApiController {

    public static final Logger LOOGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    /**
     * 根据id查询用户
     *
     * @param userId
     * @return
     */
    @GetMapping("{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable("userId") Long userId) {
        boolean server_error_flag = false;
        //定义对象
        User user = null;
        try {
            user = userService.getUserByUserId(userId);
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("根据用户id查询用户失败！程序出错！--------------------------> methodName : UserContentController.findUserContentByUserId");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == user) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        //200
        return ResponseEntity.ok(user);
    }
}
