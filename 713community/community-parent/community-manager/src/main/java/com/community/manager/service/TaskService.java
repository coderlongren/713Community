package com.community.manager.service;

import com.community.manager.entity.Task;
import com.community.manager.vo.PageResultVo;

/**
 * @Author:王喜
 * @Description : 任务信息接口
 * @Date: 2017/11/16 0016 20:27
 */
public interface TaskService {

    /**
     * 分页查询任务信息
     *
     * @param page
     * @return
     */
   PageResultVo listAllTaskByPage(Integer page);

    /**
     * 根据id删除任务
     *
     * @param taskId
     * @return
     */
    Boolean removeTaskById(Long taskId);


    /**
     * 增加任务
     *
     * @param task
     * @return
     */
    Boolean insertTaskType(Task task);

    /**
     * 根据条件模糊搜索任务信息
     *
     * @param searchKeywords  搜索关键字
     * @param searchParam  搜索条件
     * @param page  下一页
     * @return
     */
    PageResultVo listTaskWithSearch(String searchKeywords, String searchParam, Integer page);

    /**
     * 根据id查询任务内容
     * @param content_id
     * @return
     */
    Task getContentById(Long content_id);


    /**
     * 根据id更新资源信息
     *
     * @param id
     * @param task
     * @return
     */
    Boolean updateTaskById(Long id, Task task);
}
