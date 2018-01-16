package com.community.manager.vo;

import com.community.manager.entity.SysMenu;

import java.util.List;

/**
 * @Author: xian
 * @Description: 菜单栏目传输对象实体，用于角色添加、编辑、详情页面的传输
 * @Date:create in 2017/10/24 19:50
 */
public class MenusVo {

    /**
     * 顶级菜单
     */
    private List<SysMenu> topMenu;

    /**
     * 栏目
     */
    private List<SysMenu> item;

    /**
     * 按钮
     */
    private List<SysMenu> button;

    public MenusVo(List<SysMenu> topMenu, List<SysMenu> item) {
        this(topMenu, item, null);
    }

    public MenusVo(List<SysMenu> topMenu, List<SysMenu> item, List<SysMenu> button) {
        this.topMenu = topMenu;
        this.item = item;
        this.button = button;
    }

    public List<SysMenu> getTopMenu() {
        return topMenu;
    }

    public void setTopMenu(List<SysMenu> topMenu) {
        this.topMenu = topMenu;
    }

    public List<SysMenu> getItem() {
        return item;
    }

    public void setItem(List<SysMenu> item) {
        this.item = item;
    }

    public List<SysMenu> getButton() {
        return button;
    }

    public void setButton(List<SysMenu> button) {
        this.button = button;
    }
}
