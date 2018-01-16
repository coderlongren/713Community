package com.community.manager.vo;

import com.community.manager.entity.SysMenu;
import com.community.manager.entity.SysRole;

import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/10/26 19:24
 */
public class RoleWithMenusVo {

    /**
     * 角色
     */
    private SysRole role;

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


    public RoleWithMenusVo(SysRole role, List<SysMenu> topMenu, List<SysMenu> item) {
        this.role = role;
        this.topMenu = topMenu;
        this.item = item;
    }

    public RoleWithMenusVo(SysRole role, List<SysMenu> topMenu, List<SysMenu> item, List<SysMenu> button) {
        this.role = role;
        this.topMenu = topMenu;
        this.item = item;
        this.button = button;
    }

    @Override
    public String toString() {
        return "RoleWithMenusVo{" +
                "role=" + role +
                ", topMenu=" + topMenu +
                ", item=" + item +
                ", button=" + button +
                '}';
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
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
