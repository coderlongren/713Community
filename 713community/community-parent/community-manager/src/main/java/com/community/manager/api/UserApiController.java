package com.community.manager.api;

import com.community.manager.entity.User;
import com.community.manager.service.UserService;
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
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/26 19:50.
 */

@RequestMapping("api/user")
@Controller
public class UserApiController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);

    @GetMapping()
    public ResponseEntity<List<User>> listAllUser(){

        boolean server_error_flag = false;

        List<User> list = null;
        try {
            list = userService.listAllUser();
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询用户失败！程序出错！--------------------------> methodName : UserApiController.listAllUser");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == list ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(list);
    }
}
