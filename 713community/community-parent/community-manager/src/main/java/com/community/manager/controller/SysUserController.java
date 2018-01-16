package com.community.manager.controller;

import com.community.manager.entity.SysMenu;
import com.community.manager.entity.SysUser;
import com.community.manager.service.SysMenuService;
import com.community.manager.service.SysUserService;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.UserRoleVo;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xian
 * @Description: 系统用户相关的controller
 * @Date:create in 2017/10/14 21:56
 */
@RequestMapping("sys")
@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 定义日志对象，注意的是日志对象有许多个，该系统使用的是org.slf4j.Logger，import包不能错误
     * 日志对象统一定义为public static final Logger LOOGER = LoggerFactory.getLogger(SysUserController.class)
     * 不同的是SysUserController.class，这个根据日志对象所在的controller来决定
     */
    public static final Logger LOOGER = LoggerFactory.getLogger(SysUserController.class);


    /**
     * 登录页面
     *
     * @param pageName ： 页面名称
     * @return
     */
    @GetMapping("{pageName}")
    public String toPage(@PathVariable("pageName") String pageName) {
        System.out.println("=============================");
        return pageName;
    }

    /**
     *  TODO
     *
     * @return
     */
    @PostMapping("user/login")
    public ModelAndView login(@RequestParam("username") String userName,
                                @RequestParam("password") String password) {

        ModelAndView mv = new ModelAndView("index");
        //TODO 登录其他逻辑处理
        List<SysMenu> menus = sysMenuService.listAllMenu();
        List<SysMenu> menu = menus.stream().filter((e) -> e.getType() == 0).collect(Collectors.toList());
        List<SysMenu> columns = menus.stream().filter(e -> (e.getType() == 1)).collect(Collectors.toList());
        mv.addObject("menu", menu);
        mv.addObject("columns", columns);
        return mv;
    }

    /**
     * 分页查询系统管理员用户
     *
     * @param page : 下一页
     * @param rows : 每一页显示的记录数
     * @return
     */
    @GetMapping("user/page/{next}")
    public ResponseEntity<PageResultVo> viewUsersByPage(@PathVariable("next") Integer page,
                                                        @RequestParam(value = "rows", defaultValue = "10") Integer rows) {

        //定义服务器出错标志：默认为false，在捕获异常的catch里设置为true
        boolean server_error_flag = false;

        //声明分页传输对象
        PageResultVo pageResultVo = null;

        //对调用service服务处捕获异常，其他的不捕获，减少try-catch块的冗余
        try {
            //调用service层的服务，查询数据
            pageResultVo = sysUserService.listAllUserByPage(page, rows);
        } catch (Exception e) {
            //在catch处理异常处设置server_error_flag为true
            server_error_flag = true;

            //同时打上日志输出，为了将来能够迅速排错，输出均遵循一下格式,并且对于查询类的操作，只需打错误日志
            LOOGER.error("系统管理员用户分页查询失败，程序出错！ -----------------------> methodName : SysUserController.viewUsersByPage");

        }

        //如果server_error_flag为true，表示服务器出错，返回响应码500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //如果查询出的对象为空，则表示查不到数据，返回响应码404
        if (null == pageResultVo || null == pageResultVo.getRows()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //否则数据正常获取，返回响应吗200
        return ResponseEntity.ok(pageResultVo);
    }


    /**
     * 更新管理员数据
     *
     * @param userId
     * @param user
     * @return
     */
    @PutMapping("user/{userId}")
    public ResponseEntity<Void> updateSysUser(@PathVariable("userId") Long userId, SysUser user) {
        boolean server_error_flag = false;
        boolean result = false;

        try {

            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("更新管理员用户，userId = {}, SysUserData = {}",userId, user);
            }

            result = sysUserService.updateSysUserById(userId, user);

            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("更新管理员用户成功，userId = {}, SysUserData = {}",userId, user);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("更新管理员失败！程序出错！----------------------------> methodName : SysUserController.updateSysUser");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //500
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 删除管理员用户
     *
     * @param userId
     * @return
     */
    @DeleteMapping("user/{userId}")
    public ResponseEntity<Void> deleteSysUser(@PathVariable("userId") Long userId) {
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("删除管理员用户，userId = {}",userId);
            }

            result = sysUserService.removeSysUserById(userId);

            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("删除管理员用户成功，userId = {}",userId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("删除管理员失败！程序出错！----------------------------> methodName : SysUserController.deleteSysUser");

        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //500
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //200
        return ResponseEntity.ok(null);
    }

    /**
     * 根据用户id查询用户信息，并附带角色列表数据
     *
     * @param userId
     * @return
     */
    @GetMapping("user/{userId}")
    public ResponseEntity<SysUser> getSysUserById(@PathVariable("userId") Long userId) {

        boolean server_error_flag = false;

        SysUser user = null;

        try {
            user = sysUserService.getByUserIdWithRoles(userId);
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("查询管理员数据并附带角色信息失败，程序出错！--------------------> methodName : SysUserController.getSysUserById");
        }

        if (null == user) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(user);
    }

    /**
     * 添加系统用户
     *
     * @param user : 系统用户实体，它能够自动接收前台传来的参数值，前提是该实体里的属性字段名与前台传来的参数名一致
     * @param roleIds ： 角色id集合
     * @return
     */
    @PostMapping("user")
    public ResponseEntity<Void> addSysUser(SysUser user, @RequestParam("roleIds") String roleIds) {

        boolean server_error_flag = false;
        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("新增管理员用户，SysUser = {}, roleIds = {}", user, roleIds);
            }

            result = sysUserService.insertUser(user, roleIds);

            //在改变之后也需要打印日志信息
            if (LOOGER.isInfoEnabled()) {
                LOOGER.info("新增管理员用户成功，SysUser = {}, roleIds = {}", user, roleIds);
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOOGER.error("新增管理员用户失败，程序出错！-------------------------> methodName : SysUserController.addSysUser");
        }

        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据管理员名称搜索用户
     *
     * @param username
     * @param rows
     * @param page
     * @return
     */
    @GetMapping("user/{name}/page/{next}")
    public ResponseEntity<PageResultVo> searchSysUser(@PathVariable("name") String username,
                                                      @RequestParam("rows") Integer rows,
                                                      @PathVariable("next")Integer page) {

        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = sysUserService.searchByUserName(username, rows, page);
        } catch (Exception e) {
            server_error_flag = true;
            LOOGER.error("搜索管理员用户信息失败，程序出错！-----------> methodName : SysUserController.searchSysUser");
        }

        if(pageResultVo == null || pageResultVo.getRows() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if(server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(pageResultVo);
    }

}
