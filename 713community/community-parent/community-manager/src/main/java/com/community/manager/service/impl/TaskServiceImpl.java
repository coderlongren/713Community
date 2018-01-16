package com.community.manager.service.impl;

import com.community.manager.dao.TaskDao;
import com.community.manager.entity.Task;
import com.community.manager.service.TaskService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author:王喜
 * @Description :任务信息实现类
 * @Date: 2017/11/16 0016 20:27
 */
@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskDao taskDao;

    /**
     * 分页查询任务信息
     *
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAllTaskByPage(Integer page) {
        if (page <= 0 || null == page) {
            page = 1;
        }
        int rows = 10;
        /*
         * page:表示下一页
         * rows:表示每一页显示的记录数
         */
        PageHelper.startPage(page, rows);
        List<Task> list=taskDao.listAll();

        PageInfo<Task> pageInfo=new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(),pageInfo.getList(),pageInfo.getPages());
    }

    /**
     * 根据id删除任务
     *
     * @param taskId
     * @return
     */
    @Override
    public Boolean removeTaskById(Long taskId) {
        return taskDao.deleteById(taskId) == 1;
    }

    /**
     * 增加任务
     *
     * @param task
     * @return
     */
    @Override
    public Boolean insertTaskType(Task task) {
        //为task设置时间属性
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        return taskDao.insert(task) == 1;
    }

    /**
     * 根据条件模糊搜索任务信息
     *
     * @param searchKeywords 搜索关键字
     * @param searchParam    搜索条件
     * @param page           下一页
     * @return
     */
    @Override
    public PageResultVo listTaskWithSearch(String searchKeywords, String searchParam, Integer page) {
        if (page <= 0 || null == page) {
            page = 1;
        }
        int rows = 10;
        /*
         * page:表示下一页
         * rows:表示每一页显示的记录数
         */
        //拼接模糊查询的字符串，，拼接%，不在mapper中进行拼接,对关键字进行拼接
        searchKeywords = new StringBuilder("%").append(searchKeywords).append("%").toString();

        PageHelper.startPage(page, rows);
        List<Task> list = taskDao.listTaskWithSearch(searchParam,searchKeywords);

        PageInfo<Task> pageInfo= new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(),pageInfo.getList(),pageInfo.getPages());
    }

    /**
     * 根据id查询任务内容
     *
     * @param content_id
     * @return
     */
    @Override
    public Task getContentById(Long content_id) {
        return taskDao.getById(content_id);
    }


    /**
     * 根据id更新资源信息
     *
     * @param id
     * @param task
     * @return
     */
    @Override
    public Boolean updateTaskById(Long id, Task task) {
        task.setId(id);
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        return taskDao.update(task) == 1;
    }
}
