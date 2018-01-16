package com.community.manager.controller;

import com.community.manager.entity.DynamicComment;
import com.community.manager.service.DynamicCommentService;
import com.community.manager.vo.DynamicCommentVo;
import com.community.manager.vo.PageResultVo;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:23.
 */

@Controller
@RequestMapping("dynamiccomment")
public class DynamicCommentController {

    private static Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private DynamicCommentService dynamicCommentService;

    /**
     * 分页查询动态评论
     * @param page
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewAllDynamicCommentByPages(@PathVariable("next") Integer page){

        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = dynamicCommentService.listAllDynamicCommentByPage(page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询DynamicComment失败！程序出错！--------------------------> methodName :DynamicCommentController.viewAllDynamicCommentByPage");
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
     * 搜索查询动态评论
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */

    @GetMapping("/param/page/{next}")
    public ResponseEntity<PageResultVo> viewDynamicCommentWithSearch(@RequestParam("searchVal") String searchVal,
                                                                         @PathVariable("next") Integer page,
                                                                         @RequestParam(value = "rows",defaultValue = "10") Integer rows){
        Boolean server_error_flag = false;
        PageResultVo pageResultVo = null;

        try {
            pageResultVo = dynamicCommentService.listDynamicCommentWithSearch(searchVal,page,rows);

        }catch (Exception e){
            server_error_flag = true;
            LOGGER.error("模糊查询DynamicComment失败！程序出错！--------------------------> methodName :DynamicCommentController.viewAllDynamicCommentWithSearch");
        }

        //500
        if(server_error_flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if(null==pageResultVo||null==pageResultVo.getRows()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(pageResultVo);
    }

    /**
     * 根据id查询动态评论
     *
     * @param dynamicCommentId
     * @return
     */
    @GetMapping("{dynamicCommentId}")
    public ResponseEntity<DynamicComment> viewDynamicCommentById(@PathVariable("dynamicCommentId") Long dynamicCommentId){
        boolean server_error_flag = false;
        //定义对象
        DynamicComment dynamicComment = null;
        try {
            dynamicComment = dynamicCommentService.getDynamicCommentById(dynamicCommentId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询失败！程序出错！--------------------------> methodName : DynamicCommentController.getDynamicCommentById");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == dynamicComment) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(dynamicComment);

    }

    /**
     * 增加动态评论
     *
     * @param dynamicComment
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> addDynamicComment(DynamicComment dynamicComment){

        boolean server_error_flag = false;
        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增dynamicComment，DnamicComment = {}", dynamicComment);
            }

            result = dynamicCommentService.insertDynamicComment(dynamicComment);

            //在改变之后也需要打印日志信息
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增dynamicComment成功，DynamicComment = {}", dynamicComment);
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOGGER.error("新增dynamicComment失败，程序出错！-------------------------> methodName : dynamicCommentController.addDynamicComment");
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
     * 根据id删除动态评论
     *
     * @param dynamicCommentId
     * @return
     */
    @DeleteMapping("{dynamicCommentId}")
    public ResponseEntity<Void> removeDynamicCommentById(@PathVariable("dynamicCommentId") Long dynamicCommentId){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除动态评论，articleId = {}",dynamicCommentId);
            }

            result = dynamicCommentService.removeDynamicCommentById(dynamicCommentId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除动态评论成功，articleId = {}",dynamicCommentId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除动态评论失败！程序出错！----------------------------> methodName : DynamicCommentController.dynamicComment");

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

}
