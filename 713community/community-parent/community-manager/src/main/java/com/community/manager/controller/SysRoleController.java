package com.community.manager.controller;

import com.community.manager.entity.SysMenu;
import com.community.manager.entity.SysRole;
import com.community.manager.service.SysMenuService;
import com.community.manager.service.SysRoleService;
import com.community.manager.vo.MenusVo;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.RoleWithMenusVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xian
 * @Description: 角色相关控制器
 * @Date:create in 2017/10/24 19:53
 */

@RequestMapping("role")
@Controller
public class SysRoleController {

    public static final Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 根据角色id查询角色-附带角色所拥有的菜单权限
     *
     * @param roleId
     * @return
     */
    @GetMapping("{roleId}")
    public ResponseEntity<RoleWithMenusVo> viewMenuByRoleId(@PathVariable("roleId") Long roleId) {
        boolean server_error_flag = false;

        List<SysMenu> menus = null;
        List<SysMenu> menu = null;
        List<SysMenu> columns = null;
        SysRole role = null;
        try {
            role = sysRoleService.getRoleWithMenuByRoleId(roleId);
            if (null != role) {
                menus = role.getMenus();
            }
            if (null == menus || menus.isEmpty()) {
                //404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            menu = menus.stream().filter((e) -> e.getType() == 0).collect(Collectors.toList());
            columns = menus.stream().filter(e -> (e.getType() == 1)).collect(Collectors.toList());
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询角色-菜单列表失败！程序出错---------------------------> methodName : SysRoleController.viewMenuByRoleId");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //200
        return ResponseEntity.ok(new RoleWithMenusVo(role, menu, columns));
    }


    /**
     * 获取所有角色列表数据
     *
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<SysRole>> queryAllRole() {
        boolean server_error_log = false;
        List<SysRole> roles = null;

        try {
            roles = sysRoleService.listAllRole();
        } catch (Exception e) {
            server_error_log = true;
            LOGGER.error("查询所有角色列表数据失败！程序出错--------------------------> methodName : SysRoleController.queryAllRole");
        }

        if (null == roles) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (server_error_log) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(roles);
    }

    /**
     * 获取所有的菜单栏目数据
     *
     * @return
     */
    @GetMapping("menus")
    public ResponseEntity<MenusVo> viewAllMenu() {
        boolean server_error_flag = false;

        List<SysMenu> menus = null;
        List<SysMenu> menu = null;
        List<SysMenu> columns = null;
        try {
            menus = sysMenuService.listAllMenu();
            if (null == menus || menus.isEmpty()) {
                //404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            menu = menus.stream().filter((e) -> e.getType() == 0).collect(Collectors.toList());
            columns = menus.stream().filter(e -> (e.getType() == 1)).collect(Collectors.toList());
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询菜单列表失败！程序出错---------------------------> methodName : SysRoleController.viewAllMenu");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //200
        return ResponseEntity.ok(new MenusVo(menu, columns));
    }

    /**
     * 添加角色数据
     *
     * @param role
     * @param ids
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> addRole(SysRole role, String ids) {

        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增角色------------> role = {}", role);
            }

            result = sysRoleService.insertRole(role, ids);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增角色成功------------> role = {}", role);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("新增失败！程序报错！----------------> methodNmae : SysRoleController.addRole");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 分页查询角色列表数据
     *
     * @param page : 下一页
     * @param rows : 每页显示的记录数
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewRoleWithPage(@PathVariable("next") Integer page,
                                                         @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        boolean server_error_flag = false;
        PageResultVo pageResultVo = null;
        try {
            pageResultVo = sysRoleService.listRoleByPage(page, rows);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询失败！程序出错！------------------------> methodName : SysRoleController.viewRoleWithPage");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (null == pageResultVo || pageResultVo.getRows() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(pageResultVo);
    }

    /**
     * 根据id更改角色的启用状态
     *
     * @param roleId
     * @param status
     * @return
     */
    @PutMapping("{roleId}")
    public ResponseEntity<Void> editRole(@PathVariable("roleId") Long roleId, @RequestParam("status") Integer status) {
        boolean server_error_flag = false;
        int result = 0;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新角色状态>>>>>>>>>>>>>>> status = {}", status);
            }

            result = sysRoleService.updateRoleById(roleId, status);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新角色状态成功>>>>>>>>>>>>>>> status = {}", status);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("更新角色状态失败，程序出错！----------------------------> mathodName : SysRoleController.editRole");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (result == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 根据id删除角色
     *
     * @param roleId
     * @return
     */
    @DeleteMapping("{roleId}")
    public ResponseEntity<Void> removeRoleById(@PathVariable("roleId") Long roleId) {
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除角色>>>>>>>>>>>>>>> roleId = {}", roleId);
            }

            result = sysRoleService.removeRoleById(roleId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除角色成功>>>>>>>>>>>>>>> roleId = {}", roleId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除角色失败！程序出错！----------------------> mathodName : SysRoleController.removeRoleById");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(null);
    }
}
