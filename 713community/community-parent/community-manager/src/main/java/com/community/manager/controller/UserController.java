package com.community.manager.controller;

import com.community.manager.entity.User;
import com.community.manager.service.UserService;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.UserEditDataVo;
import com.github.pagehelper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: xian
 * @Description: 网站用户相关的controller层
 * @Date:create in 2017/10/31 16:22
 */
@RequestMapping("user")
@Controller
public class
UserController {

    public static final Logger LOOGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 分页查询网站用户
     *
     * @param page
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewAllUserByPage(@PathVariable("next") Integer page) {

        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = userService.listAllUserByPage(page);
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("分页查询用户失败！程序出错！--------------------------> methodName : UserController.viewAllUserByPage");
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
     * 根据id查询用户
     *
     * @param userId
     * @return
     */
    @GetMapping("{userId}")
    public ResponseEntity<UserEditDataVo> viewUserById(@PathVariable("userId") Long userId) {

        boolean server_error_flag = false;

        UserEditDataVo userEditDataVo = null;
        try {
            userEditDataVo = userService.getUserById(userId);
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("分页查询用户失败！程序出错！--------------------------> methodName : UserController.viewAllUserByPage");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == userEditDataVo) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(userEditDataVo);
    }


    /**
     * 根据用户名模糊查询用户
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */

    @GetMapping("param/page/{next}")
    public ResponseEntity<PageResultVo> viewUserWithSearch(@RequestParam("searchVal") String searchVal,
                                                           @PathVariable("next") Integer page,
                                                           @RequestParam(value = "rows",defaultValue = "10") Integer rows){
        Boolean server_error_falg = false ;
        if(StringUtil.isEmpty(searchVal)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        PageResultVo pageResultVo = null;

        try {
            if(LOOGER.isInfoEnabled()){
             LOOGER.info("根据用户名称模糊查询用户----------------------->method:UserController.viewUserWithSearch");
            }
            pageResultVo = userService.listUserWithSearch(searchVal,page,rows);
            if(LOOGER.isInfoEnabled()){
                LOOGER.info("根据用户名模糊查询用户成功------------------->method:UserController.viewUserWithSearch");
            }

        }catch (Exception e){
            server_error_falg = true;
            LOOGER.error("根据用户名模糊查询用户失败---------------------------》method：UserController.viewUserById");
        }

        //500
        if(server_error_falg){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //404
        if(null == pageResultVo || pageResultVo.getRows().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(pageResultVo);

    }


    /**
     * 更新用户
     *
     * @param userId
     * @param user
     * @return
     */
    @PutMapping("{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") Long userId, User user) {
        boolean server_error_flag = false;

        boolean result = false;
        try {
            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("更新用户，userId = {}, user = {}",userId, user);
            }

            result = userService.updateUserById(userId, user);

            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("更新用户成功，userId = {}, user = {}",userId, user);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("更新用户失败！程序出错！--------------------------> methodName : UserController.updateUser");
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
     * 根据id删除用户
     *
     * @param userId
     * @return
     */
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId){

        Boolean server_error_flag = false;
        Boolean result = false;

        try{
            if (LOOGER.isInfoEnabled()){
                LOOGER.info("根据id删除用户-------------------->method : UserController.deleteUser");
            }
            result = userService.deleteUserById(userId);
            if(LOOGER.isInfoEnabled()){
                LOOGER.info("删除用户成功-------------------->method : UserController.deleteUser");
            }

        }catch (Exception e){
            server_error_flag = true;
            LOOGER.error("删除用户失败---------------------------->method:UserController.deleteUser");
        }
        //500
        if(server_error_flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if(result){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(null);
    }


    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> insertUser(User user){

        Boolean server_error_flag = false;
        Integer res = null;

        try {
            if(LOOGER.isInfoEnabled()){
                LOOGER.info("新增用户--------------------------->method:UserController:insertUser");
            }
            res = userService.insertUser(user);
            if(LOOGER.isInfoEnabled()) {
                LOOGER.info("新增用户成功--------------------------->method:UserController.insertUser");
            }
        }catch (Exception e){
            server_error_flag = true;
            LOOGER.error("新增用户失败--------------------------->method:UserController.insertUser");
        }

        //500
        if(server_error_flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //插入失败
        if (null==res||0==res){
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //201
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
