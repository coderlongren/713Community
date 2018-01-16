package com.community.resource.controller;

import com.community.resource.entity.ResourceComment;
import com.community.resource.entity.User;
import com.community.resource.service.ResourceCommentService;
import com.community.resource.service.ResourceListService;
import com.community.resource.vo.PageResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author:王喜
 * @Description :资源评论Controller层
 * @Date: 2017/12/26 0026 9:53
 */
@Controller
@RequestMapping("/resource/comment/")
public class ResourceCommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceCommentController.class);

    @Autowired
    private ResourceCommentService resourceCommentService;

    @Autowired
    private ResourceListService resourceListService;

    /**
     * 根据资源id查询出所有的评论（分页）
     * @param resourceId
     * @param page
     * @return
     */
    @GetMapping("{resourceId}/page/{next}")
    public ResponseEntity<PageResultVo> viewResourceCommentByPage(@PathVariable("resourceId") Long resourceId,@PathVariable("next") Integer page,HttpSession httpSession) {

        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = resourceCommentService.listAllResourceCommentByPage(resourceId,page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("资分页查询资源评论信息失败！程序出错！--------------------------> methodName : ResourceCommentController.viewResourceCommentByPage");
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
     * 分别查询每一个普通用户评论
     *
     * @param commentIds
     * @return
     */
    @GetMapping("/{commentIds}")
    public ResponseEntity<List<ResourceComment>> viewResourceCommonCommentByPage(@PathVariable("commentIds") String commentIds) {
        boolean server_error_flag = false;

        List<ResourceComment> resourceComments = null;

        try {
            resourceComments = resourceCommentService.listAllResourceCommonComments(commentIds);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询资源评论普通用户信息失败！程序出错！--------------------------> methodName : ResourceCommentController.viewResourceCommonCommentByPage");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == resourceComments) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(resourceComments);
    }

    /**
     * 增加一条资源评论（评论楼主信息）
     *
     * @param resourceId
     * @param resourceComment
     * @return
     */
    @PostMapping("{resourceId}")
    public ResponseEntity<ResourceComment> commentResourceForLZ(@PathVariable("resourceId") Long resourceId,ResourceComment resourceComment) {
        //参数自动绑定：当用javabean接收参数时，不能加注解@RequestParam，否则会报错的

        boolean server_error_flag = false;
        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增资源对楼主评论信息，ResourceComment = {}", resourceComment);
            }

            result = resourceCommentService.insertResourceComment(resourceComment);
            //在改变之后也需要打印日志信息
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增资源对楼主评论信息成功，ResourceComment = {}", resourceComment);
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOGGER.error("新增资源对楼主评论信息失败，程序出错！-------------------------> methodName : ResourceCommentController.commentResource");
        }
        //500
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        //201
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 增加回复普通评论信息
     *
     * @param resourceId
     * @param resourceComment
     * @return
     */
    @PostMapping("/common/{resourceId}")
    public ResponseEntity<Void> commentResourceForCommon(@PathVariable("resourceId") Long resourceId,ResourceComment resourceComment) {
        boolean server_error_flag = false;
        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增资源对普通评论信息，ResourceComment = {}", resourceComment);
            }

            result = resourceCommentService.insertResourceCommentForCommon(resourceComment);
            //在改变之后也需要打印日志信息
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增资源对普通评论信息成功，ResourceComment = {}", resourceComment);
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOGGER.error("新增资源对普通评论信息失败，程序出错！-------------------------> methodName : ResourceCommentController.commentResource");
        }
        //500
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        //201
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * 根据登录的用户名查询用户信息
     *
     * @param username
     * @return
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserInfoByUsername(@PathVariable("username") String username) {
        boolean server_error_flag = false;

        User user = null;

        try {
            user = resourceListService.findUserInfoByUsername(username);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("根据登录用户名查询该用户信息失败！程序出错！--------------------------> methodName : ResourceCommentController.getUserInfoByUsername");
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
