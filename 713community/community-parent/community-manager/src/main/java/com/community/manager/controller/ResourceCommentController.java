package com.community.manager.controller;
import com.community.manager.entity.ResourceComment;
import com.community.manager.service.ResourceCommentService;
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
 * @Description:资源评论控制层
 * @Date:2017/11/16 19:23.
 */

@Controller
@RequestMapping("resourcecomment")
public class ResourceCommentController {

    private static Logger LOGGER = LoggerFactory.getLogger(ResourceCommentController.class);

    @Autowired
    private ResourceCommentService resourceCommentService;

    /**
     * 分页查询资源评论
     * @param page
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewAllResourceCommentByPages(@PathVariable("next") Integer page){

        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = resourceCommentService.listAllResourceCommentByPage(page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询资源评论失败！程序出错！--------------------------> methodName :ResourceCommentController.viewAllResourceCommentByPage");
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
     * 模糊搜索资源评论
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/param/page/{next}")
    public ResponseEntity<PageResultVo> viewResourceCommentWithSearch(@RequestParam("searchVal") String searchVal,
                                                                         @PathVariable("next") Integer page,
                                                                         @RequestParam(value = "rows",defaultValue = "10") Integer rows){
        Boolean server_error_flag = false;
        PageResultVo pageResultVo = null;

        try {
            pageResultVo = resourceCommentService.listResourceCommentWithSearch(searchVal,page,rows);

        }catch (Exception e){
            server_error_flag = true;
            LOGGER.error("模糊查询ResourceComment失败！程序出错！--------------------------> methodName :ResourceCommentController.viewAllResourceCommentWithSearch");
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
     * 根据id查询资源评论
     *
     * @param ResourceCommentId
     * @return
     */
    @GetMapping("{ResourceCommentId}")
    public ResponseEntity<ResourceComment> viewResourceCommentById(@PathVariable("ResourceCommentId") Long ResourceCommentId){
        boolean server_error_flag = false;
        //定义对象
        ResourceComment ResourceComment = null;
        try {
            ResourceComment = resourceCommentService.getResourceCommentById(ResourceCommentId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("根据id查询资源评论失败！程序出错！--------------------------> methodName : ResourceCommentController.viewResourceCommentById");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == ResourceComment) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(ResourceComment);

    }

    /**
     * 增加资源评论
     *
     * @param ResourceComment
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> addResourceComment(ResourceComment ResourceComment){

        boolean server_error_flag = false;
        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增ResourceComment，ResourceComment = {}", ResourceComment);
            }

            result = resourceCommentService.insertResourceComment(ResourceComment);

            //在改变之后也需要打印日志信息
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增ResourceComment，ResourceComment = {}", ResourceComment);
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOGGER.error("新增ResourceComment失败，程序出错！-------------------------> methodName : ResourceCommentController.addResourceComment");
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
     * 根据id删除资源评论
     *
     * @param ResourceCommentId
     * @return
     */
    @DeleteMapping("{ResourceCommentId}")
    public ResponseEntity<Void> removeResourceCommentById(@PathVariable("ResourceCommentId") Long ResourceCommentId){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除资源评论，articleId = {}",ResourceCommentId);
            }

            result = resourceCommentService.removeResourceCommentById(ResourceCommentId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除资源评论，ResourceCommentId = {}",ResourceCommentId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除资源评论！程序出错！----------------------------> methodName : ResourceCommentController.removeResourceCommentById");

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
