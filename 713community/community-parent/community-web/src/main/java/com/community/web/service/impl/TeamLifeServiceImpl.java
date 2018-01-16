package com.community.web.service.impl;

import com.community.common.service.ApiService;
import com.community.web.bean.Image;
import com.community.web.service.TeamLifeService;
import com.community.web.vo.TeamLifeVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:chenfq
 * @Description: 生活点滴service层的实现类
 * @Date:2017/11/26 21:21.
 */

@Service
public class TeamLifeServiceImpl implements TeamLifeService{

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ApiService apiService;

    @Value("${MANAGER_URL}")
    private String MANAGER_URL;

    @Value("${TEAM_LIFE_TYPE_IDS}")
    private String TEAM_LIFE_TYPE_IDS;

    @Value("${IMG_URL}")
    private String IMG_URL;

    /**
     *
     * 调用apiService请求url返回指定类型id集的图片数据
     *
     * @return
     * @throws IOException
     */

    @Override
    public TeamLifeVo listTeamLife() throws IOException {
        //
        List<Image> teamLifeTop = null;
        List<Image> teamLifeButtom = null;

        String url = MANAGER_URL+IMG_URL+"?typeIds="+TEAM_LIFE_TYPE_IDS;
        String jsonData = null;

        try{
            jsonData = apiService.doGet(url);
        }catch (Exception e){
            e.printStackTrace();
        }

        List<Image> list = MAPPER.readValue(jsonData,new TypeReference<List<Image>>(){});

        teamLifeTop = list.stream().filter((e) -> e.getTypeId()==6).collect(Collectors.toList());
        teamLifeButtom = list.stream().filter(e ->(e.getTypeId()==8)).collect(Collectors.toList());

        return new TeamLifeVo(teamLifeTop,teamLifeButtom);
    }
}
