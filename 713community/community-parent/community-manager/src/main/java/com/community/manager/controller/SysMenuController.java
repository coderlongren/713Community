package com.community.manager.controller;

import com.community.manager.entity.SysMenu;
import com.community.manager.service.SysMenuService;
import com.community.manager.vo.PageResultVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: xian
 * @Description: 菜单相关，控制层
 * @Date:create in 2017/10/19 11:37
 */
@RequestMapping("menu")
@Controller
public class SysMenuController {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static final Logger LOGGER = LoggerFactory.getLogger(SysMenuController.class);

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 根据菜单类型查询菜单列表
     *
     * @param type
     * @return
     */
    @GetMapping("type/{type}")
    public ResponseEntity<List<SysMenu>> listMenuByType(@PathVariable("type") Integer type) {

        boolean server_error_flag = false;
        List<SysMenu> menus = null;
        try {
            menus = sysMenuService.listMenuByType(type);
        } catch (Exception e) {
            LOGGER.error("查询出错 ------------------------> methodName : SysMenuControler.listMenuByType");
            server_error_flag = true;
        }

        //程序报错 - 500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //结果为空 - 404
        if (null == menus || menus.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        //200
        return ResponseEntity.ok(menus);
    }


    /**
     * 菜单搜索
     *
     * @param searchParam ： 搜索条件
     * @param searchVal ： 对应指定搜索条件的值
     * @return
     */
    @GetMapping("param/page/{next}")
    public ResponseEntity<PageResultVo> searchMenus(@RequestParam("searchParam") String searchParam,
                                                    @RequestParam("searchVal") String searchVal,
                                                    @PathVariable("next") Integer page,
                                                    @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        boolean server_error_flag = false;

        if (StringUtils.isEmpty(searchVal)) {
            //请求参数有误/空
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        PageResultVo pageResult = null;
        try {
            pageResult = sysMenuService.listMenuWithSearch(searchParam, searchVal, page, rows);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("程序出错 ---------------> methodName : SysMenuController.searchMenus");
        }

        if (null == pageResult || pageResult.getRows().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(pageResult);
    }

    /**
     * 根据id查询菜单栏目数据
     *
     * @param menuId
     * @return
     */
    @GetMapping("{menuId}")
    public ResponseEntity<SysMenu> getMenuById(@PathVariable("menuId") Long menuId) {

        SysMenu menu = null;
        boolean server_error_flag = false;

        try {
            menu = sysMenuService.getMenuById(menuId);
        } catch (Exception e) {
            LOGGER.error("查询出错 ---------------------------> methodName : SysMenuControler.getMenuById");
            server_error_flag = true;
        }

        //程序报错 - 500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //结果为空 - 404
        if (null == menu) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        //200
        return ResponseEntity.ok(menu);

    }

    /**
     * 菜单相关页面跳转
     *
     * @param page
     * @return
     */
    @GetMapping("{page}.html")
    public String toPage(@PathVariable("page") String page) {
        return page;
    }


    /**
     * 新增菜单数据
     *
     * @param menu
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> saveMenu(SysMenu menu) {
        boolean result = false;
        boolean server_error_flag = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增菜单 -->> menu = {}", menu);
            }

            result = sysMenuService.insertMenu(menu);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增菜单成功 -->> menu = {}", menu.getMenuName());
            }
        } catch (Exception e) {
            LOGGER.error("新增失败 -->> menu = {}", menu.getMenuName());
            server_error_flag = true;
        }

        //程序报错
        if (server_error_flag) {
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        //更新失败
        if (!result) {
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        //新增成功,201
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 更新菜单栏目数据
     *
     * @param menuId
     * @param menu
     * @return
     */
    @PutMapping("{menuId}")
    public ResponseEntity<Void> updateMenu(@PathVariable("menuId") Long menuId, SysMenu menu) {

        boolean result = false;
        boolean server_error_flag = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新菜单 -->> menu = {}", menu);
            }

            result = sysMenuService.updateMenu(menuId, menu);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新菜单成功 -->> menu = {}", menu.getMenuName());
            }
        } catch (Exception e) {
            LOGGER.error("更新失败 -->> menu = {}", menu.getMenuName());
            server_error_flag = true;
        }

        //程序报错
        if (server_error_flag) {
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        //更新失败
        if (!result) {
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        //修改成功,204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 分页查询菜单列表
     *
     * @param page : 下一页，默认为1
     * @param rows ： 每页显示的记录数
     * @return
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> allMenu(@PathVariable("next") Integer page,
                                @RequestParam(value = "rows", defaultValue = "10") Integer rows) {

        boolean server_error_flag = false;
        PageResultVo pageResult = null;
        try {
            pageResult = sysMenuService.listAllMenuByPage(page, rows);
        } catch (Exception e) {
            LOGGER.error("分页查询出错 ---------------------------> methodName : SysMenuControler.allMenu");
            server_error_flag = true;
        }

        //程序报错 - 500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //结果为空 - 404
        if (null == pageResult || null == pageResult.getRows()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        //200
        return ResponseEntity.ok(pageResult);

    }

    /**
     * 删除菜单栏目
     *
     * @param menuId
     * @return
     */
    @DeleteMapping("{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable("menuId") Long menuId) {
        boolean server_error_flag = false;
        boolean result = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除菜单数据 ----------------> menuId = {}",menuId);
            }

            result = sysMenuService.removeMenuById(menuId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除菜单数据成功 ----------------> menuId = {}",menuId);
            }
        } catch (Exception e) {
            LOGGER.error("删除失败！！程序出错-----------------> methodName : SysMenuControler.deleteMenu");
            server_error_flag = true;
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(null);
    }
}
