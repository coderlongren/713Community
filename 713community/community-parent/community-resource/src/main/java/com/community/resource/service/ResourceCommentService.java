package com.community.resource.service;

import com.community.resource.entity.ResourceComment;
import com.community.resource.vo.PageResultVo;

import java.io.IOException;
import java.util.List;

/**
 * @Author:王喜
 * @Description :资源评论Service层
 * @Date: 2017/12/26 0026 9:50
 */
public interface ResourceCommentService {
    /**
     * 根据id查询评论
     *
     * @param resourceId
     * @param page
     * @return
     * @throws IOException
     */
    PageResultVo listAllResourceCommentByPage(Long resourceId,Integer page) throws IOException;

    /**
     * 查询所有对每一个普通用户的评论
     *
     * @param commentIds
     * @return
     */
    List<ResourceComment> listAllResourceCommonComments(String commentIds) throws IOException;

    /**
     * 增加资源评论(对楼主的评论)
     *
     * @param resourceComment
     * @return
     */
    Boolean insertResourceComment(ResourceComment resourceComment);

    /**
     * 回复普通评论
     *
     * @param resourceComment
     * @return
     */
    Boolean insertResourceCommentForCommon(ResourceComment resourceComment);

}
