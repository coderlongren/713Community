package com.community.manager.service.impl;

import com.community.manager.dao.ResourceHistoryCommentDao;
import com.community.manager.entity.ResourceHistoryComment;
import com.community.manager.service.ResourceHistoryCommentService;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.ResourceCommentUserVo;
import com.community.manager.vo.ResourceHistoryCommentVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:王喜
 * @Description:资源历史评论接口实现类
 * @Date:2017/11/16 19:31.
 */

@Service
public class ResourceHistoryCommentServiceImpl implements ResourceHistoryCommentService {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ResourceHistoryCommentDao resourceHistoryCommentDao;


    /**
     * 分页查询资源历史评论
     *
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAllResourceHistoryCommentByPage(Integer page) {
        //测试数据{"username"："chen","userUrl":"qq.com"}
        if (page <= 0 || null == page) {
            page = 1;
        }
        int rows = 10;
        /*
         * page:表示下一页
         * rows:表示每一页显示的记录数
         */
        PageHelper.startPage(page, rows);
        //所有查出来的动态评论
        List<ResourceHistoryComment> list = resourceHistoryCommentDao.listAll();

        //得到数据库的总记录
        PageInfo<ResourceHistoryComment> pageInfo1 = new PageInfo<>(list);

        //要返回的Vo数据
        List<ResourceHistoryCommentVo> resourceHistoryCommentVos = ResourceHistoryCommentServiceImpl.getCommentVoData(list);
        PageInfo<ResourceHistoryCommentVo> pageInfo = new PageInfo<>(resourceHistoryCommentVos);
        return new PageResultVo(pageInfo1.getTotal(),pageInfo.getList(),pageInfo1.getPages());
    }

    /**
     * 根据id查询资源历史评论
     *
     * @param id
     * @return
     */
    @Override
    public ResourceHistoryComment getResourceHistoryCommentById(Long id) {
        return resourceHistoryCommentDao.getById(id);
    }

    /**
     * 根据id增加资源历史评论
     *
     * @param resourceHistoryComment
     * @return
     */
    @Override
    public Boolean insertResourceHistoryComment(ResourceHistoryComment resourceHistoryComment) {

        resourceHistoryComment.setCreateTime(new Date());
        resourceHistoryComment.setUpdateTime(new Date());
        return resourceHistoryCommentDao.insert(resourceHistoryComment)==1;
    }

    /**
     * 根据id删除资源历史评论
     *
     * @param resourceHistoryCommentId
     * @return
     */
    @Override
    public Boolean removeResourceHistoryCommentById(Long resourceHistoryCommentId) {
        return resourceHistoryCommentDao.deleteById(resourceHistoryCommentId)==1;
    }

    /**
     * 模糊搜索资源历史评论
     *
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listResourceHistoryCommentWithSearch(String searchVal, Integer page, Integer rows) {
        if(page<=0|| null == page){
            page=1;
        }
        PageHelper.startPage(page,rows);

        //所有查出来的动态评论
        String val = new StringBuffer("%").append(searchVal).append("%").toString();
        List<ResourceHistoryComment> resourceHistoryComments = resourceHistoryCommentDao.listAllWithSearch(val);

        //得到数据库记录的总数
        PageInfo<ResourceHistoryComment> pageInfo1 = new PageInfo<>(resourceHistoryComments);

        List<ResourceHistoryCommentVo>resourceHistoryCommentVos = ResourceHistoryCommentServiceImpl.getCommentVoData(resourceHistoryComments);
        PageInfo<ResourceHistoryCommentVo> pageInfo = new PageInfo<>(resourceHistoryCommentVos);
        return new PageResultVo(pageInfo1.getTotal(),pageInfo.getList(),pageInfo1.getPages());
    }

    //自定义方法
    public static List<ResourceHistoryCommentVo> getCommentVoData(List<ResourceHistoryComment> resourceHistoryComments){

        List<ResourceHistoryCommentVo> resourceHistoryCommentVos = new ArrayList<>();

        //新建一个评论用户类
        ResourceCommentUserVo resourceCommentUserVo = new ResourceCommentUserVo();
        String jsonData = null;
        for (ResourceHistoryComment resourceCommentWithSearch:resourceHistoryComments) {
            //创建单个动态评论Vo实体接收动态评论的内容，再将username属性变成CommentUserVo类型
            ResourceHistoryCommentVo resourceHistoryCommentVo =new ResourceHistoryCommentVo();
            resourceHistoryCommentVo.setResourceHistoryComment(resourceCommentWithSearch);

            jsonData = resourceCommentWithSearch.getUsername();

            try {
                resourceCommentUserVo = (ResourceCommentUserVo) MAPPER.readValue(jsonData, new TypeReference<ResourceCommentUserVo>(){});

            }catch(Exception e){
                e.printStackTrace();
            }
            resourceHistoryCommentVo.setResourceCommentUserVo(resourceCommentUserVo);

            resourceHistoryCommentVos.add(resourceHistoryCommentVo);
        }
        jsonData = null;
        resourceCommentUserVo = null;

        return resourceHistoryCommentVos;
    }
}
