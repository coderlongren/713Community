package com.community.manager.controller;

import com.community.manager.entity.ForumComment;
import com.community.manager.service.ForumCommentService;
import com.community.manager.vo.PageResultVo;
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
@RequestMapping("forumcomment")
public class ForumCommentController {

    private static Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ForumCommentService forumCommentService;

    /**
     * 分页查询论坛评论
     * @param page
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewAllForumCommentByPages(@PathVariable("next") Integer page){

        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = forumCommentService.listAllForumCommentByPage(page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询ForumComment失败！程序出错！--------------------------> methodName :ForumCommentController.viewAllForumCommentByPage");
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
     * 搜索查询论坛评论
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/param/page/{next}")
    public ResponseEntity<PageResultVo> viewForumCommentWithSearch(@RequestParam("searchVal") String searchVal,
                                                                         @PathVariable("next") Integer page,
                                                                         @RequestParam(value = "rows",defaultValue = "10") Integer rows){
        Boolean server_error_flag = false;
        PageResultVo pageResultVo = null;

        try {
            pageResultVo = forumCommentService.listForumCommentWithSearch(searchVal,page,rows);

        }catch (Exception e){
            server_error_flag = true;
            LOGGER.error("模糊查询ForumComment失败！程序出错！--------------------------> methodName :ForumCommentController.viewAllForumCommentWithSearch");
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
     * 根据id查询论坛评论
     *
     * @param ForumCommentId
     * @return
     */
    @GetMapping("{ForumCommentId}")
    public ResponseEntity<ForumComment> viewForumCommentById(@PathVariable("ForumCommentId") Long ForumCommentId){
        boolean server_error_flag = false;
        //定义对象
        ForumComment forumComment = null;
        try {
            forumComment = forumCommentService.getForumCommentById(ForumCommentId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询失败！程序出错！--------------------------> methodName : ForumCommentController.getForumCommentById");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == forumComment) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(forumComment);

    }

    /**
     * 增加论坛评论
     *
     * @param ForumComment
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> addForumComment(ForumComment ForumComment){

        boolean server_error_flag = false;
        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增ForumComment，DnamicComment = {}", ForumComment);
            }

            result = forumCommentService.insertForumComment(ForumComment);

            //在改变之后也需要打印日志信息
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增ForumComment成功，ForumComment = {}", ForumComment);
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOGGER.error("新增ForumComment失败，程序出错！-------------------------> methodName : ForumCommentController.addForumComment");
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
     * 根据id删除论坛评论
     *
     * @param ForumCommentId
     * @return
     */
    @DeleteMapping("{ForumCommentId}")
    public ResponseEntity<Void> removeArticleById(@PathVariable("ForumCommentId") Long ForumCommentId){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除论坛评论，articleId = {}",ForumCommentId);
            }

            result = forumCommentService.removeForumCommentById(ForumCommentId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除论坛评论成功，articleId = {}",ForumCommentId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除论坛评论失败！程序出错！----------------------------> methodName : ForumCommentController.ForumComment");

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
