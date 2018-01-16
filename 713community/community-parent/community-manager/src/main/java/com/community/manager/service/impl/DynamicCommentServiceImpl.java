package com.community.manager.service.impl;

import com.community.manager.dao.DynamicCommentDao;
import com.community.manager.entity.DynamicComment;
import com.community.manager.service.DynamicCommentService;
import com.community.manager.vo.CommentUserVo;
import com.community.manager.vo.DynamicCommentVo;
import com.community.manager.vo.PageResultVo;
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
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:31.
 */

@Service
public class DynamicCommentServiceImpl implements DynamicCommentService{

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private DynamicCommentDao dynamicCommentDao;

    /**
     * 分页查询动态评论
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAllDynamicCommentByPage(Integer page) {
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
        List<DynamicComment> list = dynamicCommentDao.listAll();

        //新建pageInfo获取查出来的总数和总页数
        PageInfo<DynamicComment> pageInfoTemp = new PageInfo<>(list);
        Long total = pageInfoTemp.getTotal();
        Integer pages = pageInfoTemp.getPages();

        //要返回的Vo数据
        List<DynamicCommentVo> dynamicCommentVos = DynamicCommentServiceImpl.getCommentVoData(list);
        PageInfo<DynamicCommentVo> pageInfo = new PageInfo<>(dynamicCommentVos);
        return new PageResultVo(total,pageInfo.getList(),pages);
    }

    /**
     * 根据id查询动态评论
     * @param id
     * @return
     */
    @Override
    public DynamicComment getDynamicCommentById(Long id) {
        return dynamicCommentDao.getById(id);
    }

    /**
     * 插入动态评论
     * @param dynamicComment
     * @return
     */
    @Override
    public Boolean insertDynamicComment(DynamicComment dynamicComment) {

        dynamicComment.setCreateTime(new Date());
        dynamicComment.setUpdateTime(new Date());
        return dynamicCommentDao.insert(dynamicComment)==1;
    }

    /**
     * 根据id删除动态评论
     * @param dynamicCommentId
     * @return
     */
    @Override
    public Boolean removeDynamicCommentById(Long dynamicCommentId) {
        return dynamicCommentDao.deleteById(dynamicCommentId)==1;
    }

    /**
     * 搜索查询动态评论
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listDynamicCommentWithSearch(String searchVal, Integer page, Integer rows) {
        if(page<=0|| null == page){
            page=1;
        }
        PageHelper.startPage(page,rows);

        //所有查出来的动态评论
        String val = new StringBuffer("%").append(searchVal).append("%").toString();
        List<DynamicComment> dynamicComments = dynamicCommentDao.listAllWithSearch(val);
        PageInfo<DynamicComment> pageInfoTemp = new PageInfo<>(dynamicComments);
        List<DynamicCommentVo> dynamicCommentVos = DynamicCommentServiceImpl.getCommentVoData(dynamicComments);
//        //新建一个返回的DynamicCommentVo类
//        List<DynamicCommentVo> dynamicCommentVos = new ArrayList<>();
//
//        //新建一个评论用户类
//        CommentUserVo commentUserVo = new CommentUserVo();
//        String jsonData = null;
//        for (DynamicComment dynamicCommentWithSearch:dynamicComments) {
//            //创建单个动态评论Vo实体接收动态评论的内容，再将username属性变成CommentUserVo类型
//            DynamicCommentVo dynamicCommentVo =new DynamicCommentVo();
//            dynamicCommentVo.setDynamicComment(dynamicCommentWithSearch);
//
//            jsonData = dynamicCommentWithSearch.getUsername();
//
//            try {
//                commentUserVo = (CommentUserVo) MAPPER.readValue(jsonData, new TypeReference<CommentUserVo>(){});
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            dynamicCommentVo.setCommentUserVo(commentUserVo);
//
//            dynamicCommentVos.add(dynamicCommentVo);
//        }
//        jsonData = null;
//        commentUserVo = null;
        PageInfo<DynamicCommentVo> pageInfo = new PageInfo<>(dynamicCommentVos);
        return new PageResultVo(pageInfoTemp.getTotal(),pageInfo.getList(),pageInfoTemp.getPages());
    }

    /**
     * 将动态评论中的username转化成commentUserVo类，再用动态评论Vo类接收
     * @param dynamicComments
     * @return
     */

    public static List<DynamicCommentVo> getCommentVoData(List<DynamicComment> dynamicComments){

        List<DynamicCommentVo> dynamicCommentVos = new ArrayList<>();

        //新建一个评论用户类
        CommentUserVo commentUserVo = new CommentUserVo();
        String jsonData = null;
        for (DynamicComment dynamicCommentWithSearch:dynamicComments) {
            //创建单个动态评论Vo实体接收动态评论的内容，再将username属性变成CommentUserVo类型
            DynamicCommentVo dynamicCommentVo =new DynamicCommentVo();
            dynamicCommentVo.setDynamicComment(dynamicCommentWithSearch);

            jsonData = dynamicCommentWithSearch.getUsername();

            try {
                commentUserVo = (CommentUserVo) MAPPER.readValue(jsonData, new TypeReference<CommentUserVo>(){});

            }catch(Exception e){
                e.printStackTrace();
            }
            dynamicCommentVo.setCommentUserVo(commentUserVo);

            dynamicCommentVos.add(dynamicCommentVo);
        }
        jsonData = null;
        commentUserVo = null;

        return dynamicCommentVos;
    }
}
