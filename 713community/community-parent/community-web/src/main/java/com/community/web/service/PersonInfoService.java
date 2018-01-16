package com.community.web.service;

import com.community.web.vo.PersonInfoVo;

import java.io.IOException;

/**
 * @Author:王喜
 * @Description :个人中心Service
 * @Date: 2017/11/28 0028 16:05
 */
public interface PersonInfoService {
    /**
     * 得到个人介绍信息
     *
     * @return
     */
    PersonInfoVo getPersonInfoInfo(Long userId) throws IOException;
}
