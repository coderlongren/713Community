package com.community.manager.service;

import com.community.manager.entity.SysMenu;
import com.community.manager.vo.PageResultVo;

import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/10/18 16:19
 */
public interface SysMenuService {

    /**
     * 按照orderNum顺序查询所有菜单栏目
     * TODO 后期加上按钮级别的栏目，需修改查询
     * @return
     */
    List<SysMenu> listAllMenu();

    /**
     *
     * 按照orderNum顺序分页查询所有菜单栏目
     *
     * @param page : 指定的页码
     * @param rows ： 每页显示的记录数
     * @return : 分数数据传输对象
     */
    PageResultVo listAllMenuByPage(Integer page, Integer rows);

    /**
     * 根据菜单栏目类型查询菜单栏目
     *
     * @param type : 类型 0-顶级菜单；1-栏目；2-按钮
     * @return
     */
    List<SysMenu> listMenuByType(Integer type);

    /**
     * 根据id查询菜单栏目
     *
     * @param menuId
     * @return
     */
    SysMenu getMenuById(Long menuId);

    /**
     * 更新菜单栏目数据
     *
     * @param menu
     * @return
     */
    boolean updateMenu(Long menuId, SysMenu menu);

    /**
     * 新增菜单数据
     *
     * @param menu
     * @return
     */
    boolean insertMenu(SysMenu menu);

    /**
     * 根据id删除菜单数据
     *
     * @param menuId
     * @return
     */
    boolean removeMenuById(Long menuId);

    /**
     * 菜单搜索--分页查询
     *
     * @param searchParam ： 搜索条件
     * @param searchVal ： 对应指定搜索条件的值
     * @return
     */
    PageResultVo listMenuWithSearch(String searchParam, String searchVal, Integer page, Integer rows);
}
