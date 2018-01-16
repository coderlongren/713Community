package com.community.manager.service;

import com.community.manager.entity.UserContent;
import com.community.manager.vo.UserContentVo;

/**
 * @Author:王喜
 * @Description :用户内容Service
 * @Date: 2017/11/27 0027 19:30
 */
public interface UserContentService {

    /**
     * 根据用户id查询用户内容
     *
     * @param userId
     * @return
     */
    UserContentVo getUserContentByUserId(Long userId);
}
