package com.community.manager.service.impl;

import com.community.manager.dao.SysMenuDao;
import com.community.manager.entity.SysMenu;
import com.community.manager.service.SysMenuService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/10/18 16:19
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    /**
     * 按照orderNum顺序查询所有菜单栏目
     * TODO 后期加上按钮级别的栏目，需修改查询
     * @return
     */
    @Override
    public List<SysMenu> listAllMenu() {
        return sysMenuDao.listAllMenu();
    }

    /**
     *
     * 按照orderNum顺序分页查询所有菜单栏目
     *
     * @param page : 指定的页码
     * @param rows ： 每页显示的记录数
     * @return : 分数数据传输对象
     */
    public PageResultVo listAllMenuByPage(Integer page, Integer rows) {
        //为了提升系统的健壮性，先判断page是否为空，为空则设一个默认值1
        if (page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page, rows);
        List<SysMenu> list = sysMenuDao.listAllMenu();
        PageInfo<SysMenu> pageInfo = new PageInfo<>(list);

        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 根据菜单栏目类型查询菜单栏目
     *
     * @param type : 类型 0-顶级菜单；1-栏目；2-按钮
     * @return
     */
    @Override
    public List<SysMenu> listMenuByType(Integer type) {
        //type==1，表示当前选择的是栏目，则要查出顶级菜单，一下同理
        if (type == 1) {
            type = 0;
        }
        if (type == 2) {
            type = 1;
        }
        return sysMenuDao.listByType(type);
    }

    /**
     * 根据id查询菜单栏目
     *
     * @param menuId
     * @return
     */
    @Override
    public SysMenu getMenuById(Long menuId) {
        return sysMenuDao.getMenuById(menuId);
    }

    /**
     * 更新菜单栏目数据
     *
     * @param menu
     * @return
     */
    @Override
    public boolean updateMenu(Long menuId, SysMenu menu) {
        menu.setUpdateTime(new Date());
        return sysMenuDao.updateMenu(menu) == 1;
    }

    /**
     * 新增菜单数据
     *
     * @param menu
     * @return
     */
    @Override
    public boolean insertMenu(SysMenu menu) {
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        //如果parentId为null，则为顶级菜单，需要默认设该记录的父级菜单为0
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        return sysMenuDao.insertMenu(menu) == 1;
    }

    /**
     * 根据id删除菜单数据
     *
     * @param menuId
     * @return
     */
    @Override
    public boolean removeMenuById(Long menuId) {
        //查询parentId为menuId的菜单数据集合，然后删除属于该menuId下的子菜单栏目
        List<Integer> ids = sysMenuDao.listMenuByParentId(menuId);
        if (null != ids && ids.size() > 0) {
            //集合式删除 TODO 采用mybatis的批量删除功能
            sysMenuDao.removeMenuByIds(ids);
        }

        int result = sysMenuDao.removeMenuById(menuId);
        return result == 1;
    }

    /**
     * 菜单搜索--分页查询
     *
     * @param searchParam ： 搜索条件
     * @param searchVal ： 对应指定搜索条件的值
     * @return
     */
    @Override
    public PageResultVo listMenuWithSearch(String searchParam, String searchVal, Integer page, Integer rows) {
        //为了提升系统的健壮性，先判断page是否为空，为空则设一个默认值1
        if (page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page, rows);
        String value = new StringBuilder("%").append(searchVal).append("%").toString();
        List<SysMenu> list = sysMenuDao.listMenuWithSearch(searchParam, value);
        PageInfo<SysMenu> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }
}
