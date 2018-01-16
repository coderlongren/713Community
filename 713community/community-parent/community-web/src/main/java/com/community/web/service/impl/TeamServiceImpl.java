package com.community.web.service.impl;

import com.community.common.service.ApiService;
import com.community.web.bean.User;
import com.community.web.service.TeamService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/26 11:07.
 */

@Service
public class TeamServiceImpl implements TeamService{

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ApiService apiService;

    @Value("${MANAGER_URL}")
    private String MANAGE_URL;

    @Value("${USER_URL}")
    private String USER_URL;

    @Override
    public List<User> listTeam() throws IOException{

        String url = MANAGE_URL+USER_URL;

//        List<User> list = null;

        String jsonData = apiService.doGet(url);

        if(jsonData==null){
            return null;
        }

        List<User> list = MAPPER.readValue(jsonData,new TypeReference<List<User>>() {});

        return list;
    }
}
