package com.community.resource.service;

import com.community.resource.vo.IndexVo;

import java.io.IOException;

/**
 * @Author:王喜
 * @Description :资源共享首页Service接口
 * @Date: 2017/12/19 0019 9:48
 */
public interface IndexService {

    /**
     * 访问资源共享首页数据
     *
     * @return
     */
    IndexVo listResourceIndexInfo() throws IOException;

}
