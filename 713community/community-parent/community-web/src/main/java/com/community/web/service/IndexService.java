package com.community.web.service;


import com.community.web.bean.Image;
import com.community.web.vo.IndexVo;

import java.io.IOException;
import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/11/16 9:51
 */
public interface IndexService {

    /**
     * 访问首页，获取图片
     *
     * @return
     */
    IndexVo listImageByTypeIds() throws IOException;

    /**
     * 获取首页导航栏
     *
     * @return
     */
    IndexVo listIndexNav() throws IOException;
}
