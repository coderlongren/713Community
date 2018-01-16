package com.community.resource.service.impl;

import com.community.resource.dao.ResourceCommentDao;
import com.community.resource.dao.ResourceInfoDao;
import com.community.resource.dao.UserDao;
import com.community.resource.entity.ResourceComment;
import com.community.resource.entity.User;
import com.community.resource.service.ResourceCommentService;
import com.community.resource.vo.PageResultVo;
import com.community.resource.vo.ResourceCommentUserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:王喜
 * @Description :资源评论Service层实现类
 * @Date: 2017/12/26 0026 9:50
 */
@Service
public class ResourceCommentServiceImpl implements ResourceCommentService {

    //json插件
    private static final ObjectMapper Mapper = new ObjectMapper();

    @Autowired
    private ResourceCommentDao resourceCommentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ResourceInfoDao resourceInfoDao;
    /**
     * 根据id查询评论
     *
     * @param resourceId
     * @param page
     * @return
     * @throws IOException
     */

    @Override
    public PageResultVo listAllResourceCommentByPage(Long resourceId, Integer page) throws IOException {


        if (page < 1 || page == null) {
            page = 1;
        }
        //每页显示10条评论
        int rowCount = 10;
        //调用mybatis分页插件
        PageHelper.startPage(page,rowCount);
        /**
         *
         * 1.查询出所有的资源评论
         * 2.把json格式的usernmae,url解析出来
         * 3.根据usernmae查询出单个用户的信息,然后再把用户放到list集合中
         * 4.再把user信息放入到resourceComment中
         */
        List<ResourceComment> resourceComments = resourceCommentDao.listAllCommentByResourceId(resourceId);

        //把json文本数据格式化成json格式数据（查询出来的user也是一个list集合）
        //遍历resourceComments集合

        for (ResourceComment resourceComment : resourceComments) {
            String jsonData = resourceComment.getUsername();
            ResourceCommentUserVo resourceCommentUserVo = new ResourceCommentUserVo();
            User user = new User();
            resourceCommentUserVo = (ResourceCommentUserVo) Mapper.readValue(jsonData, new TypeReference<ResourceCommentUserVo>(){});
            //根据用户名查询用户信息
            user = userDao.getUserInfoByUsername(resourceCommentUserVo.getUsername());
            //获取该用户（楼主）的发布资源总数
            Integer resourceReleaseCount = resourceInfoDao.getResourceReleaseCount(user.getId());
            user.setResourceReleaseCount(resourceReleaseCount);
            //每条评论对应一个用户信息
            resourceComment.setUser(user);
            resourceComment.setResourceCommentUserVo(resourceCommentUserVo);
        }

        PageInfo<ResourceComment> pageInfo = new PageInfo<>(resourceComments);

        //返回上一层
        return new PageResultVo(pageInfo.getTotal(),pageInfo.getList(),pageInfo.getPages());
    }

    /**
     * 查询所有对每一个普通用户的评论
     *
     * @param commentIds
     * @return
     */
    @Override
    public List<ResourceComment> listAllResourceCommonComments(String commentIds) throws IOException {
        if (null == commentIds || "" == commentIds) {
            return null;
        }
        String[] ids = commentIds.split(",");
        List<Long> listCommentIds = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            listCommentIds.add(Long.valueOf(ids[i]));
        }

        List<ResourceComment> commonComments = resourceCommentDao.listByCommentIds(listCommentIds);

        for (ResourceComment resourceComment : commonComments) {
            String jsonData = resourceComment.getUsername();

            ResourceCommentUserVo resourceCommentUserVo = (ResourceCommentUserVo) Mapper.readValue(jsonData, new TypeReference<ResourceCommentUserVo>(){});
            //根据用户名查询用户信息
            User user = userDao.getUserInfoByUsername(resourceCommentUserVo.getUsername());
            //获取该用户（楼主）的发布资源总数
            Integer resourceReleaseCount = resourceInfoDao.getResourceReleaseCount(user.getId());
            user.setResourceReleaseCount(resourceReleaseCount);
            //每条评论对应一个用户信息
            resourceComment.setUser(user);
            resourceComment.setResourceCommentUserVo(resourceCommentUserVo);
        }


        return commonComments;
    }

    /**
     * 增加资源评论(对楼主的评论)
     *
     * @param resourceComment
     * @return
     */
    @Override
    public Boolean insertResourceComment(ResourceComment resourceComment) {


        if (resourceComment == null) {
            return  false;
        }
        //对楼主评论，parentId设置为0
        resourceComment.setParentId(Long.parseLong("0"));
        resourceComment.setFloor(resourceComment.getFloor() + 1);

        String jsonData = transferObjectToJsonText();

        resourceComment.setUsername(jsonData);
        resourceComment.setImg(null);
        resourceComment.setReleaseTime(new Date());
        resourceComment.setUpdateTime(new Date());
        resourceComment.setCreateTime(new Date());

        Integer insertResourceCommentResult = resourceCommentDao.insertComment(resourceComment);

        return insertResourceCommentResult == 1;
    }

    /**
     * 回复普通评论
     *
     * @param resourceComment
     * @return
     */
    @Override
    public Boolean insertResourceCommentForCommon(ResourceComment resourceComment) {
        //回复普通用户，楼层设置为0
        resourceComment.setFloor(0);
        resourceComment.setUsername(transferObjectToJsonText());
        resourceComment.setImg(null);
        resourceComment.setReleaseTime(new Date());
        resourceComment.setUpdateTime(new Date());
        resourceComment.setCreateTime(new Date());

        Integer result = resourceCommentDao.insertComment(resourceComment);

        return result == 1;
    }

    //自定义方法，把ResourceCommentUserVo格式化为字符串文本存入数据库
    public String transferObjectToJsonText() {
        //从session中获取
        String username = "wangxi";
        String userUrl = "http://personal.community.com/user/" + username;
        ResourceCommentUserVo resourceCommentUserVo = new ResourceCommentUserVo(username,userUrl);
        //把资源评论名称,url格式化成json格式数据
        String jsonData = null;
        try {
            jsonData = Mapper.writeValueAsString(resourceCommentUserVo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
}
