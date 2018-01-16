package com.community.manager.service.impl;

import com.community.manager.dao.ForumCommentDao;
import com.community.manager.entity.ForumComment;
import com.community.manager.service.ForumCommentService;
import com.community.manager.vo.CommentUserVo;
import com.community.manager.vo.ForumCommentVo;
import com.community.manager.vo.PageResultVo;
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
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:31.
 */

@Service
public class ForumCommentServiceImpl implements ForumCommentService{

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ForumCommentDao forumCommentDao;

    /**
     * 分页查询论坛评论
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAllForumCommentByPage(Integer page) {
        //测试数据{"username":"chen","userurl":"qq.com"}
        if (page <= 0 || null == page) {
            page = 1;
        }
        int rows = 10;
        /*
         * page:表示下一页
         * rows:表示每一页显示的记录数
         */
        PageHelper.startPage(page, rows);
        //所有查出来的论坛评论
        List<ForumComment> list = forumCommentDao.listAll();

        //新建pageInfo返回要查出来的总条数和总页数
        PageInfo<ForumComment> pageInfoTemp = new PageInfo<>(list);
        //要返回的Vo数据
        List<ForumCommentVo> ForumCommentVos = ForumCommentServiceImpl.getCommentVoData(list);
//        List<ForumCommentVo> ForumCommentVos = new ArrayList<>();
//
//        String jsonData = null;
//
////        List<CommentUserVo> commentUserVos = new ArrayList<>();
//        CommentUserVo commentUserVo = new CommentUserVo();
//
//        for(ForumComment ForumComment:list){
//
//            //创建单个论坛评论Vo实体接收论坛评论的内容，再将username属性变成CommentUserVo类型
//            ForumCommentVo ForumCommentVo = new ForumCommentVo();
//            ForumCommentVo.setForumComment(ForumComment);
//
//            jsonData = ForumComment.getUsername();
//
//            //利用MAPPER把json格式数据转换成CommentUserVo类型
//            try {
//                commentUserVo =(CommentUserVo) MAPPER.readValue(jsonData,new TypeReference<CommentUserVo>() {  });
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            ForumCommentVo.setCommentUserVo(commentUserVo);
//
//            ForumCommentVos.add(ForumCommentVo);
//
//        }
//
//        jsonData = null;
//        commentUserVo = null;
        PageInfo<ForumCommentVo> pageInfo = new PageInfo<>(ForumCommentVos);
        return new PageResultVo(pageInfoTemp.getTotal(),pageInfo.getList(),pageInfoTemp.getPages());
    }

    /**
     * 根据id查询论坛评论
     * @param id
     * @return
     */

    @Override
    public ForumComment getForumCommentById(Long id) {
        return forumCommentDao.getById(id);
    }

    /**
     * 插入论坛评论
     * @param ForumComment
     * @return
     */
    @Override
    public Boolean insertForumComment(ForumComment ForumComment) {

        ForumComment.setCreateTime(new Date());
        ForumComment.setUpdateTime(new Date());
        return forumCommentDao.insert(ForumComment)==1;
    }

    /**
     * 根据id删除论坛评论
     *
     * @param ForumCommentId
     * @return
     */

    @Override
    public Boolean removeForumCommentById(Long ForumCommentId) {
        return forumCommentDao.deleteById(ForumCommentId)==1;
    }


    /**
     * 搜索论坛评论
     *
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listForumCommentWithSearch(String searchVal, Integer page, Integer rows) {
        if(page<=0|| null == page){
            page=1;
        }
        PageHelper.startPage(page,rows);

        //所有查出来的论坛评论
        String val = new StringBuffer("%").append(searchVal).append("%").toString();
        List<ForumComment> ForumComments = forumCommentDao.listAllWithSearch(val);

        //新建pageInfo获取取到的总页数，总数据条数
        PageInfo<ForumComment> pageInfoTemp = new PageInfo<>(ForumComments);

        List<ForumCommentVo> ForumCommentVos = ForumCommentServiceImpl.getCommentVoData(ForumComments);
//        //新建一个返回的ForumCommentVo类
//        List<ForumCommentVo> ForumCommentVos = new ArrayList<>();
//
//        //新建一个评论用户类
//        CommentUserVo commentUserVo = new CommentUserVo();
//        String jsonData = null;
//        for (ForumComment ForumCommentWithSearch:ForumComments) {
//            //创建单个论坛评论Vo实体接收论坛评论的内容，再将username属性变成CommentUserVo类型
//            ForumCommentVo ForumCommentVo =new ForumCommentVo();
//            ForumCommentVo.setForumComment(ForumCommentWithSearch);
//
//            jsonData = ForumCommentWithSearch.getUsername();
//
//            try {
//                commentUserVo = (CommentUserVo) MAPPER.readValue(jsonData, new TypeReference<CommentUserVo>(){});
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            ForumCommentVo.setCommentUserVo(commentUserVo);
//
//            ForumCommentVos.add(ForumCommentVo);
//        }
//        jsonData = null;
//        commentUserVo = null;
        PageInfo<ForumCommentVo> pageInfo = new PageInfo<>(ForumCommentVos);
        return new PageResultVo(pageInfoTemp.getTotal(),pageInfo.getList(),pageInfoTemp.getPages());
    }

    //抽取共用代码：将查出来的username转化成commentUserVo类，最终返回论坛Vo类

    public static List<ForumCommentVo> getCommentVoData(List<ForumComment> ForumComments){

        List<ForumCommentVo> forumCommentVos = new ArrayList<>();

        //新建一个评论用户类
        CommentUserVo commentUserVo = new CommentUserVo();
        String jsonData = null;
        for (ForumComment forumCommentWithSearch:ForumComments) {
            //创建单个论坛评论Vo实体接收论坛评论的内容，再将username属性变成CommentUserVo类型
            ForumCommentVo forumCommentVo =new ForumCommentVo();
            forumCommentVo.setForumComment(forumCommentWithSearch);

            jsonData = forumCommentWithSearch.getUsername();

            try {
                commentUserVo = (CommentUserVo) MAPPER.readValue(jsonData, new TypeReference<CommentUserVo>(){});

            }catch(Exception e){
                e.printStackTrace();
            }
            forumCommentVo.setCommentUserVo(commentUserVo);

            forumCommentVos.add(forumCommentVo);
        }
        jsonData = null;
        commentUserVo = null;

        return forumCommentVos;
    }
}
