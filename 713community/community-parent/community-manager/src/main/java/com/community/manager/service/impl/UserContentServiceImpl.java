package com.community.manager.service.impl;

import com.community.manager.dao.UserContentDao;
import com.community.manager.entity.UserContent;
import com.community.manager.service.UserContentService;
import com.community.manager.vo.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author:王喜
 * @Description :用户内容Service实现类
 * @Date: 2017/11/27 0027 19:30
 */
@Service
public class UserContentServiceImpl implements UserContentService{

    @Autowired
    private UserContentDao userContentDao;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 根据用户id查询用户内容
     *
     * @param userId
     * @return
     */
    @Override
    public UserContentVo getUserContentByUserId(Long userId) {
        UserContent userContent = new UserContent();
        userContent = userContentDao.getById(userId);

        UserContentVo userContentVo = null;

        if (null != userContent) {

            userContentVo = new UserContentVo(userContent);
        }

        return userContentVo;
    }
}
