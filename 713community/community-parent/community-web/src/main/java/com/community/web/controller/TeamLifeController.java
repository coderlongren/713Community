package com.community.web.controller;

import com.community.web.service.TeamLifeService;
import com.community.web.vo.TeamLifeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author:chenfq
 * @Description:生活点滴的控制层
 * @Date:2017/11/26 21:16.
 */

@RequestMapping("rest/team/life")
@Controller
public class TeamLifeController {

    private static final Logger LOGGER= LoggerFactory.getLogger(TeamLifeController.class);

    @Autowired
    private TeamLifeService teamLifeService;

    /**
     * 返回指定生活点滴Vo数据
     *
     * @return
     */

    @GetMapping()
    public ResponseEntity<TeamLifeVo> viewTeamLife(){

        Boolean server_error_flag = false;

        TeamLifeVo teamLifeVo = null;
        try {

            teamLifeVo = teamLifeService.listTeamLife();

        }catch (Exception e){
            server_error_flag = true;
        }

        //500
        if(server_error_flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if(null==teamLifeVo){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(teamLifeVo);
    }

}


