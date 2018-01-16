package com.community.manager.service.impl;

import com.community.manager.dao.ResourceCommentDao;
import com.community.manager.dao.ResourceHistoryCommentDao;
import com.community.manager.entity.ResourceComment;
import com.community.manager.entity.ResourceHistoryComment;
import com.community.manager.service.ResourceCommentService;
import com.community.manager.service.ResourceHistoryCommentService;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.ResourceCommentUserVo;
import com.community.manager.vo.ResourceCommentVo;
import com.community.manager.vo.ResourceHistoryCommentVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:王喜
 * @Description:资源评论接口实现类
 * @Date:2017/11/16 19:31.
 */

@Service
public class ResourceCommentServiceImpl implements ResourceCommentService {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ResourceCommentDao resourceCommentDao;


    /**
     * 分页查询资源评论
     *
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAllResourceCommentByPage(Integer page) {
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
        List<ResourceComment> list = resourceCommentDao.listAll();

        PageInfo<ResourceComment> pageInfo1 = new PageInfo<>(list);
        //要返回的Vo数据
        List<ResourceCommentVo> resourceCommentVos = ResourceCommentServiceImpl.getCommentVoData(list);
        PageInfo<ResourceCommentVo> pageInfo = new PageInfo<>(resourceCommentVos);
        return new PageResultVo(pageInfo1.getTotal(),pageInfo.getList(),pageInfo1.getPages());
    }

    /**
     * 根据id查询资源评论
     *
     * @param id
     * @return
     */
    @Override
    public ResourceComment getResourceCommentById(Long id) {
        return resourceCommentDao.getById(id);
    }

    /**
     * 根据id增加资源评论
     *
     * @param resourceComment
     * @return
     */
    @Override
    public Boolean insertResourceComment(ResourceComment resourceComment) {

        resourceComment.setCreateTime(new Date());
        resourceComment.setUpdateTime(new Date());
        return resourceCommentDao.insert(resourceComment)==1;
    }

    /**
     * 根据id删除资源评论
     *
     * @param resourceCommentId
     * @return
     */
    @Override
    public Boolean removeResourceCommentById(Long resourceCommentId) {
        return resourceCommentDao.deleteById(resourceCommentId)==1;
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
    public PageResultVo listResourceCommentWithSearch(String searchVal, Integer page, Integer rows) {
        if(page<=0|| null == page){
            page=1;
        }
        PageHelper.startPage(page,rows);


        //所有查出来的资源评论
        String val = new StringBuffer("%").append(searchVal).append("%").toString();
        List<ResourceComment> resourceComments = resourceCommentDao.listAllWithSearch(val);

        PageInfo<ResourceComment> pageInfo1 = new PageInfo<>(resourceComments);

        List<ResourceCommentVo> resourceCommentVos = ResourceCommentServiceImpl.getCommentVoData(resourceComments);
        PageInfo<ResourceCommentVo> pageInfo = new PageInfo<>(resourceCommentVos);


        return new PageResultVo(pageInfo1.getTotal(),pageInfo.getList(),pageInfo1.getPages());
    }

    //自定义方法
    public static List<ResourceCommentVo> getCommentVoData(List<ResourceComment> resourceComments){

        List<ResourceCommentVo> resourceCommentVos = new ArrayList<>();

        //新建一个评论用户类
        ResourceCommentUserVo resourceCommentUserVo = new ResourceCommentUserVo();
        String jsonData = null;
        for (ResourceComment resourceCommentWithSearch:resourceComments) {
            //创建单个动态评论Vo实体接收动态评论的内容，再将username属性变成CommentUserVo类型
            ResourceCommentVo resourceCommentVo =new ResourceCommentVo();
            resourceCommentVo.setResourceComment(resourceCommentWithSearch);

            jsonData = resourceCommentWithSearch.getUsername();

            try {
                resourceCommentUserVo = (ResourceCommentUserVo) MAPPER.readValue(jsonData, new TypeReference<ResourceCommentUserVo>(){});

            }catch(Exception e){
                e.printStackTrace();
            }
            resourceCommentVo.setResourceCommentUserVo(resourceCommentUserVo);

            resourceCommentVos.add(resourceCommentVo);
        }
        jsonData = null;
        resourceCommentUserVo = null;

        return resourceCommentVos;
    }
}
