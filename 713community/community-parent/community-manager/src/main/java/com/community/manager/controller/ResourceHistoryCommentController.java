package com.community.manager.controller;
import com.community.manager.entity.ResourceHistoryComment;
import com.community.manager.service.ResourceHistoryCommentService;
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
 * @Author:王喜
 * @Description:资源历史评论控制层
 * @Date:2017/11/16 19:23.
 */

@Controller
@RequestMapping("resourcecommenthistory")
public class ResourceHistoryCommentController {

    private static Logger LOGGER = LoggerFactory.getLogger(ResourceHistoryCommentController.class);

    @Autowired
    private ResourceHistoryCommentService resourceHistoryCommentService;

    /**
     * 分页查询资源历史评论
     * @param page
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewAllResourceHistoryCommentByPages(@PathVariable("next") Integer page){

        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = resourceHistoryCommentService.listAllResourceHistoryCommentByPage(page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询资源历史评论失败！程序出错！--------------------------> methodName :ResourceHistoryCommentController.viewAllResourceHistoryCommentByPage");
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
     * 模糊搜索资源历史评论
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/param/page/{next}")
    public ResponseEntity<PageResultVo> viewResourceHistoryCommentWithSearch(@RequestParam("searchVal") String searchVal,
                                                                         @PathVariable("next") Integer page,
                                                                         @RequestParam(value = "rows",defaultValue = "10") Integer rows){
        Boolean server_error_flag = false;
        PageResultVo pageResultVo = null;

        try {
            pageResultVo = resourceHistoryCommentService.listResourceHistoryCommentWithSearch(searchVal,page,rows);

        }catch (Exception e){
            server_error_flag = true;
            LOGGER.error("模糊查询ResourceHistoryComment失败！程序出错！--------------------------> methodName :ResourceHistoryCommentController.viewAllResourceHistoryCommentWithSearch");
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
     * 根据id查询资源历史评论
     *
     * @param resourceHistoryCommentId
     * @return
     */
    @GetMapping("{resourceHistoryCommentId}")
    public ResponseEntity<ResourceHistoryComment> viewResourceHistoryCommentById(@PathVariable("resourceHistoryCommentId") Long resourceHistoryCommentId){
        boolean server_error_flag = false;
        //定义对象
        ResourceHistoryComment resourceHistoryComment = null;
        try {
            resourceHistoryComment = resourceHistoryCommentService.getResourceHistoryCommentById(resourceHistoryCommentId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("根据id查询资源历史评论失败！程序出错！--------------------------> methodName : ResourceHistoryCommentController.viewResourceHistoryCommentById");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == resourceHistoryComment) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(resourceHistoryComment);

    }

    /**
     * 增加资源历史评论
     *
     * @param resourceHistoryComment
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> addResourceHistoryComment(ResourceHistoryComment resourceHistoryComment){

        boolean server_error_flag = false;
        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增resourceHistoryComment，ResourceHistoryComment = {}", resourceHistoryComment);
            }

            result = resourceHistoryCommentService.insertResourceHistoryComment(resourceHistoryComment);

            //在改变之后也需要打印日志信息
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增resourceHistoryComment，ResourceHistoryComment = {}", resourceHistoryComment);
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOGGER.error("新增resourceHistoryComment失败，程序出错！-------------------------> methodName : ResourceHistoryCommentController.addResourceHistoryComment");
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
     * 根据id删除资源历史评论
     *
     * @param resourceHistoryCommentId
     * @return
     */
    @DeleteMapping("{resourceHistoryCommentId}")
    public ResponseEntity<Void> removeResourceHistoryCommentById(@PathVariable("resourceHistoryCommentId") Long resourceHistoryCommentId){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除资源历史评论，articleId = {}",resourceHistoryCommentId);
            }

            result = resourceHistoryCommentService.removeResourceHistoryCommentById(resourceHistoryCommentId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除资源历史评论，resourceHistoryCommentId = {}",resourceHistoryCommentId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除资源历史评论！程序出错！----------------------------> methodName : ResourceHistoryCommentController.removeResourceHistoryCommentById");

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
