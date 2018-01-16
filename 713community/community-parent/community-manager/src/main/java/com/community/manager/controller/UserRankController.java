package com.community.manager.controller;

import com.community.manager.entity.UserRank;
import com.community.manager.service.UserRankService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.StringUtil;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 *
 * @Author:chenfq
 * @Description:用户等级控制器
 * @Date:2017/11/6 20:37.
 */


@RequestMapping("userrank")
@Controller
public class UserRankController {

    public static final Logger LOOGER = LoggerFactory.getLogger(UserTypeController.class);

    @Autowired
    private UserRankService userRankService;

    /**
     * 分页查询用户等级
     * @param page
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewUserRankByPage(@PathVariable("next") Integer page){

        Boolean server_error_flag = false;
        PageResultVo pageResultVo = null;

        try {
            pageResultVo = userRankService.viewUserRankByPage(page);

        }catch (Exception e){
            server_error_flag = true;
            LOOGER.error("分页查询用户等级失败！！程序出错==================methodName：UserRankService.viewUserRankByPage ");
        }

        //500
        if(server_error_flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //404
        if(null==pageResultVo||null==pageResultVo.getRows()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        //200

        return ResponseEntity.ok(pageResultVo);
    }

    /**
     * 根据id查询用户等级
     * @param id
     * @return
     */

    @GetMapping("{next}")
    public ResponseEntity<UserRank> getUserRankById(@PathVariable("next") Long id){

        Boolean server_error_Flag = false;
        UserRank userRank = null;
        try {
            userRank = userRankService.getUserRankById(id);
        }catch (Exception e){
            server_error_Flag=true;
            LOOGER.error("根据id查询userRank失败---------------->method:UserRankService.getUserRankById");
        }

        //500
        if(server_error_Flag==true){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //404
        if(null==userRank){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        //200
        return ResponseEntity.ok(userRank);
        //return null;
    }


    /**
     * 根据用户等级名称模糊查询用户等级
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/param/page/{next}")
    public ResponseEntity<PageResultVo> viewUserRankWithSearch(@RequestParam("searchVal") String searchVal,
                                                               @PathVariable("next") Integer page,
                                                               @RequestParam(value = "rows",defaultValue = "10") Integer rows){

        Boolean server_error_flag = false;
        if(StringUtil.isEmpty(searchVal)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        PageResultVo pageResultVo = null;

        try{
            if(LOOGER.isInfoEnabled()){
                LOOGER.info("根据用户等级名称模糊查询用户等级--------------->UserRankController:viewUserRankWithSearch");
            }
            pageResultVo = userRankService.listUserRankWithSearch(searchVal,page,rows);

            if(LOOGER.isInfoEnabled()){
                LOOGER.info("根据用户等级名称模糊查询用户等级成功--------------->UserRankController:viewUserRankWithSearch");
            }
        }catch (Exception e){

            server_error_flag = true;
            LOOGER.error("用户等级名称模糊查询用户等级失败--------------->UserRankController:viewUserRankWithSearch");
        }

        //500
        if (server_error_flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //404
        if(null==pageResultVo||pageResultVo.getRows().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(pageResultVo);
    }


    /**
     * 根据id删除用户等级
     * @param id
     * @return
     */
    @DeleteMapping("{next}")
    public ResponseEntity<Void> deleteUserRankById(@PathVariable("next") Long id){


        Boolean server_error_flag = false;
        Boolean result = false;

        try {
            if(LOOGER.isInfoEnabled()){
                LOOGER.info("删除用户等级：>>>>>>>>>id={}",id);
            }

            result = userRankService.removeUserById(id);

            if (LOOGER.isInfoEnabled()){
                LOOGER.info("删除角色成功：>>>>>>>>>id={}",id);
            }


        }catch (Exception e){
            server_error_flag=true;
            LOOGER.error("删除角色失败，服务器出错------------------>methodName：UserRankController:deleteUserRankById()");
        }

        //500
        if (server_error_flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        if (!result)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(null);

    }

    /**
     * 新增用户等级
     * @param userRank
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> insertUserRank(UserRank userRank){
        Boolean server_error_flag = false;
        Integer res = null;

        try {

            if(LOOGER.isInfoEnabled()){
                LOOGER.info("新增用户等级--------------->method: UserRankController.insertUserRank");
            }
            res = userRankService.insertUserRank(userRank);

            if (LOOGER.isInfoEnabled()){
                LOOGER.info("新增用户等级成功------------>method:UserRankController.insertUserRank");
            }

        }catch (Exception e){
            server_error_flag = true ;
            LOOGER.error("新增用户等级失败---------------->method:UserRankController.insertUserRank");
        }

        //500
        if(server_error_flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //新增失败
        if(null==res||0==res){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //200
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * 更新用户等级
     * @param id
     * @param userRank
     * @return
     */
    @PutMapping("{next}")
    public ResponseEntity<Void> updateUserRank(@PathVariable("next")Long id,UserRank userRank){

        boolean server_error_flag = false;
        boolean res=false;

        try {
            if (LOOGER.isInfoEnabled()){
                LOOGER.info("更新用户等级----------->UserRankController:updateUserRank");
            }
            res = userRankService.updateUserRankById(id,userRank);
            if(LOOGER.isInfoEnabled()){
                LOOGER.info("更新用户等级成功---------->UserRankController:updateUserRank");
            }

        }catch (Exception e){
            server_error_flag = true;
            LOOGER.error("更新用户等级失败--------------->UserRankController:updateUserRank");
        }

        //500
        if (server_error_flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //更新失败
        if(!res){
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 查询所有的用户等级
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<UserRank>> viewAllUserRank(){
        boolean server_error_log = false;
        List<UserRank> roles = null;

        try {
            roles = userRankService.listAllUserRank();
        } catch (Exception e) {
            server_error_log = true;
            LOOGER.error("查询所有角色列表数据失败！程序出错--------------------------> methodName : SysRoleController.queryAllRole");
        }

        if (null == roles) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (server_error_log) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(roles);
    }
}
