package com.community.manager.service.impl;

import com.community.manager.dao.DynamicCommentHistoryDao;
import com.community.manager.entity.DynamicCommentHistory;
import com.community.manager.service.DynamicCommentHistoryService;
import com.community.manager.vo.CommentUserVo;
import com.community.manager.vo.DynamicCommentHistoryVo;
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
public class DynamicCommentHistoryServiceImpl implements DynamicCommentHistoryService{

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private DynamicCommentHistoryDao dynamicCommentHistoryDao;

    /**
     * 分页查询动态评论历史
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAllDynamicCommentByPage(Integer page) {
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
        //所有查出来的动态评论
        List<DynamicCommentHistory> list = dynamicCommentHistoryDao.listAll();

        PageInfo<DynamicCommentHistory> pageInfoTemp = new PageInfo<>(list);
        //要返回的Vo数据
        List<DynamicCommentHistoryVo> dynamicCommentHistoryVos = DynamicCommentHistoryServiceImpl.getCommentVoData(list);
//        List<DynamicCommentVo> dynamicCommentVos = new ArrayList<>();
//
//        String jsonData = null;
//
////        List<CommentUserVo> commentUserVos = new ArrayList<>();
//        CommentUserVo commentUserVo = new CommentUserVo();
//
//        for(DynamicComment dynamicComment:list){
//
//            //创建单个动态评论Vo实体接收动态评论的内容，再将username属性变成CommentUserVo类型
//            DynamicCommentVo dynamicCommentVo = new DynamicCommentVo();
//            dynamicCommentVo.setDynamicComment(dynamicComment);
//
//            jsonData = dynamicComment.getUsername();
//
//            //利用MAPPER把json格式数据转换成CommentUserVo类型
//            try {
//                commentUserVo =(CommentUserVo) MAPPER.readValue(jsonData,new TypeReference<CommentUserVo>() {  });
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            dynamicCommentVo.setCommentUserVo(commentUserVo);
//
//            dynamicCommentVos.add(dynamicCommentVo);
//
//        }
//
//        jsonData = null;
//        commentUserVo = null;
        PageInfo<DynamicCommentHistoryVo> pageInfo = new PageInfo<>(dynamicCommentHistoryVos);
        return new PageResultVo(pageInfoTemp.getTotal(),pageInfo.getList(),pageInfoTemp.getPages());
    }

    /**
     * 根据id查询动态评论历史
     * @param id
     * @return
     */

    @Override
    public DynamicCommentHistory getDynamicCommentById(Long id) {
        return dynamicCommentHistoryDao.getById(id);
    }

    /**
     * 插入动态评论历史
     * @param dynamicComment
     * @return
     */
    @Override
    public Boolean insertDynamicComment(DynamicCommentHistory dynamicComment) {

        dynamicComment.setCreateTime(new Date());
        dynamicComment.setUpdateTime(new Date());
        return dynamicCommentHistoryDao.insert(dynamicComment)==1;
    }


    /**
     * 根据id删除动态评论历史
     * @param dynamicCommentId
     * @return
     */
    @Override
    public Boolean removeDynamicCommentById(Long dynamicCommentId) {
        return dynamicCommentHistoryDao.deleteById(dynamicCommentId)==1;
    }

    /**
     * 搜索动态评论历史
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
        List<DynamicCommentHistory> dynamicCommentHistories = dynamicCommentHistoryDao.listAllWithSearch(val);

        //获取查询出来的总数和，和总页数
        PageInfo<DynamicCommentHistory> pageInfoTemp = new PageInfo<>(dynamicCommentHistories);

        List<DynamicCommentHistoryVo> dynamicCommentHistoryVos = DynamicCommentHistoryServiceImpl.getCommentVoData(dynamicCommentHistories);
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
        PageInfo<DynamicCommentHistoryVo> pageInfo = new PageInfo<>(dynamicCommentHistoryVos);
        return new PageResultVo(pageInfoTemp.getTotal(),pageInfo.getList(),pageInfoTemp.getPages());
    }


    /**
     *
     * 返回指定的Vo类型
     * @param dynamicComments
     * @return
     */
    public static List<DynamicCommentHistoryVo> getCommentVoData(List<DynamicCommentHistory> dynamicComments){

        List<DynamicCommentHistoryVo> dynamicCommentHistoryVos = new ArrayList<>();

        //新建一个评论用户类
        CommentUserVo commentUserVo = new CommentUserVo();
        String jsonData = null;
        for (DynamicCommentHistory dynamicCommentWithSearch:dynamicComments) {
            //创建单个动态评论Vo实体接收动态评论的内容，再将username属性变成CommentUserVo类型
            DynamicCommentHistoryVo dynamicCommentHistoryVo =new DynamicCommentHistoryVo();
            dynamicCommentHistoryVo.setDynamicCommentHistory(dynamicCommentWithSearch);

            jsonData = dynamicCommentWithSearch.getUsername();

            try {
                commentUserVo = (CommentUserVo) MAPPER.readValue(jsonData, new TypeReference<CommentUserVo>(){});

            }catch(Exception e){
                e.printStackTrace();
            }
            dynamicCommentHistoryVo.setCommentUserVo(commentUserVo);

            dynamicCommentHistoryVos.add(dynamicCommentHistoryVo);
        }
        jsonData = null;
        commentUserVo = null;

        return dynamicCommentHistoryVos;
    }
}
