package com.community.web.service;

import com.community.web.vo.AboutUsVo;

import java.io.IOException;

/**
 * @Author:王喜
 * @Description :关于我们Service
 * @Date: 2017/11/26 0026 20:45
 */
public interface AboutUsService {

    /**
     * 获取关于我们的信息
     *
     * @return
     */
    AboutUsVo getAboutUsInfo() throws IOException;
}
