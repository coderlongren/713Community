package com.community.web.controller;

import com.community.web.bean.User;
import com.community.web.service.TeamService;
import com.community.web.vo.TeamVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:chenfq
 * @Description:团队风采controller层
 * @Date:2017/11/26 10:41.
 */

@RequestMapping("rest/team")
@Controller
public class TeamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    @GetMapping()
    public ResponseEntity<TeamVo> viewTeam(){

        Boolean server_error_flag = false;
        List<User> list = null;
        try{
            list = teamService.listTeam();

        }catch (Exception e){
            server_error_flag = true;
        }

        //500
        if (server_error_flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if(null==list){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        TeamVo teamVo = new TeamVo();
        teamVo.setUsers(list);
        return ResponseEntity.ok(teamVo);
    }
}
