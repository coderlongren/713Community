package com.community.web.service.impl;

import com.community.common.service.ApiService;
import com.community.web.bean.Community;
import com.community.web.service.AboutUsService;
import com.community.web.vo.AboutUsVo;
import com.community.web.vo.AchievementMemberVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author:王喜
 * @Description :关于我们Service实现类
 * @Date: 2017/11/26 0026 20:45
 */
@Service
public class AboutUsServiceImpl implements AboutUsService{

    //定义转换json插件
    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ApiService apiService;

    @Value("${MANAGER_URL}")
    private String MANAGER_URL;

    @Value("${COMMUNITY_URL}")
    private String COMMUNITY_URL;

    @Value("${ACHIEVEMENT_URL}")
    private String ACHIEVEMENT_URL;


    /**
     * 获取关于我们的信息
     *
     * @return
     */
    @Override
    public AboutUsVo getAboutUsInfo() throws IOException {
        //使用两个doGet方法，获得相应的数据，再封装到Vo中

        String urlCommunity = MANAGER_URL + COMMUNITY_URL;
        String urlAchievement = MANAGER_URL + ACHIEVEMENT_URL;

        String jsonData1 = apiService.doGet(urlCommunity);

        String jsonData2 = apiService.doGet(urlAchievement);

        if (null == jsonData1 || null == jsonData2 ) {
            return null;
        }
        //将json格式的数据解析成对应的集合实体
        Community community = MAPPER.readValue(jsonData1,new TypeReference<Community>() {} );

        List<AchievementMemberVo> achievementMemberVos = MAPPER.readValue(jsonData2, new TypeReference<List<AchievementMemberVo>>() {});

        AboutUsVo aboutUsVo = new AboutUsVo();
        aboutUsVo.setCommunity(community);
        aboutUsVo.setAchievements(achievementMemberVos);
        return aboutUsVo;
    }
}
