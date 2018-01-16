package com.community.manager.controller;

import com.community.manager.entity.Task;
import com.community.manager.service.TaskService;
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
 * @Description :任务信息Controller层
 * @Date: 2017/11/16 0016 20:28
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    //定义日志~
    public static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    /**
     * 分页查询任务信息
     *
     * @param page  下一页
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewTaskPage(@PathVariable("next") Integer page){
        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = taskService.listAllTaskByPage(page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询任务信息失败！程序出错！--------------------------> methodName : TaskController.viewTaskPage");
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
     * 根据id删除任务
     *
     * @param taskId
     * @return
     */
    @DeleteMapping("{taskId}")
    public ResponseEntity<Void> removeTaskTypeById(@PathVariable("taskId") Long taskId){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除任务信息，taskId = {}",taskId);
            }

            result = taskService.removeTaskById(taskId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除任务信息成功，taskId = {}",taskId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除任务信息失败！程序出错！----------------------------> methodName : TaskController.removeTaskTypeById");

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

    /**
     * 增加任务信息
     *
     * @param task
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Void> addTask(Task task){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增任务信息，Task = {}", task);
            }

            result = taskService.insertTaskType(task);

            //在改变之后也需要打印日志信息
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增任务信息成功，Task = {}", task);
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOGGER.error("新增任务信息失败，程序出错！-------------------------> methodName : TaskController.addTask");
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
     * 根据条件模糊搜任务信息
     * @param page 下一页
     * @param searchParam 搜索条件
     * @param searchKeywords 搜索关键字
     * @return
     */
    @GetMapping("pages/{next}")
    public ResponseEntity<PageResultVo> searchTaskByParamsAndKeywords(@PathVariable("next") Integer page,
                                                                          @RequestParam("searchParam") String searchParam,
                                                                          @RequestParam("searchKeywords") String searchKeywords) {

        boolean server_error_flag = false;
        PageResultVo pageResult = null;
        try {
            pageResult = taskService.listTaskWithSearch(searchKeywords,searchParam,page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("任务信息模糊搜索程序出错 ---------------> methodName : ResourceController.searchResourceByParamsAndKeywords");
        }

        //404
        if (null == pageResult || pageResult.getRows().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(pageResult);
    }

    /**
     * 根据资源id查任务信息
     *
     * @param contentId 资源id
     * @return
     */
    @GetMapping("content/{content_id}")
    public ResponseEntity<Task> getTaskContentById(@PathVariable ("content_id") Long contentId) {
        boolean server_error_flag = false;
        //定义对象
        Task task=null;
        try {
            task = taskService.getContentById(contentId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询任务内容失败！程序出错！--------------------------> methodName : TaskController.getTaskContentById");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == task) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(task);
    }

    /**
     * 更新任务信息
     *
     * @param contentId
     * @param task
     * @return
     */
    @PutMapping("{contentId}")
    public ResponseEntity<Void> updateTask(@PathVariable("contentId") Long contentId,Task task){
        boolean server_error_flag = false;

        boolean result = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新任务信息，contentId = {}, task = {}",contentId,task);
            }

            result = taskService.updateTaskById(contentId, task);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新任务信息成功，contentId = {}, task = {}",contentId, task);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("更新任务信息失败！程序出错！--------------------------> methodName : TaskController.updateTask");
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
    
}
