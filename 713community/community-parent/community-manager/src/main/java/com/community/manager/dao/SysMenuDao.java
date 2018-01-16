package com.community.manager.dao;

import com.community.manager.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/10/18 16:17
 */
public interface SysMenuDao {

    /**
     * 按照orderNum顺序查询所有菜单栏目
     *
     * @return
     */
    List<SysMenu> listAllMenu();

    /**
     * 根据菜单栏目类型查询菜单栏目
     *
     * @param type : 类型 0-顶级菜单；1-栏目；2-按钮
     * @return
     */
    List<SysMenu> listByType(Integer type);

    /**
     * 根据id查询菜单栏目
     *
     * @param menuId
     * @return
     */
    SysMenu getMenuById(Long menuId);

    /**
     * 更新菜单数据
     *
     * @param menu
     * @return
     */
    Integer updateMenu(SysMenu menu);

    /**
     * 新增菜单数据
     *
     * @param menu
     * @return
     */
    Integer insertMenu(SysMenu menu);

    /**
     * 根据id删除菜单数据
     *
     * @param menuId
     * @return
     */
    Integer removeMenuById(Long menuId);

    /**
     * 根据parentId查询菜单id集合
     *
     * @param parentId
     * @return
     */
    List<Integer> listMenuByParentId(Long parentId);

    /**
     * 以id集合式删除菜单数据，用于删除菜单栏目下的子菜单
     *
     * @param ids
     * @return
     */
    Integer removeMenuByIds(List<Integer> ids);

    /**
     * 菜单搜索
     *
     * @param searchParam ： 搜索条件
     * @param searchVal ： 对应指定搜索条件的值
     * @return
     */
    List<SysMenu> listMenuWithSearch(@Param("param") String searchParam,
                                     @Param("searchVal") String searchVal);
}
