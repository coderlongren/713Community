package com.community.manager.controller;

import com.community.manager.entity.User;
import com.community.manager.entity.UserType;
import com.community.manager.service.UserTypeService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.StringUtil;
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
 * @Author:chenfq
 * @Description:用户类型控制器
 * @Date:2017/11/2 16:03.
 */
@RequestMapping("usertype")
@Controller
public class UserTypeController {

    public static final Logger LOOGER = LoggerFactory.getLogger(UserTypeController.class);

    @Autowired
    private UserTypeService userTypeService;


    /**
     *
     * 分页查询用户类型
     * @param page：下一页
     * @return ：PageResultVo
     */

    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewUserTypeByPage(@PathVariable("next") Integer page){

        Boolean server_error_flag = false;
        PageResultVo pageResultVo = null;

        try {
            pageResultVo = userTypeService.viewUserTypeByPage(page);

        }catch (Exception e){
            server_error_flag = true;
            LOOGER.error("分页查询用户类型失败！！程序出错==================methodName：UserTypeService.viewUserTypeByPage ");
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
     * 根据id查询单条记录
     *
     * @param userTypeId
     * @return
     */

    @GetMapping("{userTypeId}")
    public ResponseEntity<UserType> getUserTypeById(@PathVariable("userTypeId") Long userTypeId){

        Boolean server_error_Flag = false;
        UserType userType = null;
        try {
            userType = userTypeService.getUserTypeById(userTypeId);
        }catch (Exception e){
            server_error_Flag=true;
            LOOGER.error("根据id查询userType失败---------------->method:UserTypeService.getUserTypeById");
        }

        //500
        if(server_error_Flag==true){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //404
        if(null==userType){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        //200
        return ResponseEntity.ok(userType);
    }

    /**
     *
     * 根据id删除对应的记录
     *
     * @param id
     * @return
     */

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserTypeById(@PathVariable("id") Long id){

        Boolean server_error_flag = false;
        Boolean result = false;

        try {
            if(LOOGER.isInfoEnabled()){
                LOOGER.info("删除用户类型：>>>>>>>>>id={}",id);
            }

            result = userTypeService.removeUserById(id);

            if (LOOGER.isInfoEnabled()){
                LOOGER.info("删除角色成功：>>>>>>>>>id={}",id);
            }


        }catch (Exception e){
            server_error_flag=true;
            LOOGER.error("删除角色失败，服务器出错------------------>methodName：UserController:deleteUserTypeById()");
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
     * 新增用户类型
     * @param userType
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> saveUserType(UserType userType){

        Boolean server_error_flag = false;
        Integer result = null;

        try {
            if (LOOGER.isInfoEnabled()){
                LOOGER.info("新增用户类型：------->userType");
            }
            result = userTypeService.insertUserType(userType);
            if(LOOGER.isInfoEnabled()){
                LOOGER.info("新增用户类型成功-------->userType:",userType.getTypeName());
            }

        }catch (Exception e){
            LOOGER.error("新增用户类型失败---------->userType:",userType.getTypeName());
            server_error_flag = true;
        }

        //响应码
        //500
        if(server_error_flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //新增失败
        if (null==result||0==result){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //201
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    /**
     *更新用户
     * @param id
     * @param userType
     * @return
     */
    @PutMapping("{userTypeId}")
    public ResponseEntity<Void> updateUserType(@PathVariable("userTypeId")Long id, UserType userType){

        boolean server_error_flag = false;

        boolean result = false;
        try {
            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("更新用户，userTypeId = {}, userType = {}",id, userType);
            }

            result = userTypeService.updateUserTypeById(id, userType);

            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("更新用户成功，userId = {}, user = {}",id, userType);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("更新用户失败！程序出错！--------------------------> methodName : UserController.updateUser");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //更新失败
        if (!result) {
            //500
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //修改成功204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 模糊查询用户类型
     *
     * @param searchVal 插入的值
     * @param page   当前页
     * @param rows   每一页的行数
     * @return
     */
    @GetMapping("param/page/{next}")
    public ResponseEntity<PageResultVo> searchUserTypes(@RequestParam("searchVal") String searchVal,
                                                        @PathVariable("next") Integer page,
                                                        @RequestParam(value = "rows",defaultValue="10") Integer rows){
        Boolean server_error_flag = false;
        if(StringUtil.isEmpty(searchVal)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        PageResultVo pageResultVo = null;

        try{
            pageResultVo = userTypeService.listUserTypeWithSearch(searchVal,page,rows);

        }catch (Exception e){
            server_error_flag = true;
            LOOGER.error("模糊查询失败------------------------>method:UserTypeController:searchUserType");
        }

        //500
        if(server_error_flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if(null==pageResultVo||pageResultVo.getRows().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(pageResultVo);
    }

    @GetMapping("list")
    public ResponseEntity<List<UserType>> getAllUserType(){
        boolean server_error_log = false;
        List<UserType> roles = null;

        try {
            roles = userTypeService.listAllUserType();
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
