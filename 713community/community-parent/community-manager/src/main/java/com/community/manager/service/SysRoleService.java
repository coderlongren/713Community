package com.community.manager.service;


import com.community.manager.entity.SysRole;
import com.community.manager.vo.PageResultVo;

import java.util.List;

/**
 * @Author: xian
 * @Description: 角色业务逻辑处理层
 * @Date:create in 2017/10/25 15:34
 */
public interface SysRoleService {

    /**
     * 新增角色数据
     *
     * @param role
     * @param ids
     * @return
     */
    Boolean insertRole(SysRole role, String ids);

    /**
     * 分页查询角色数据
     *
     * @param page : 下一页
     * @param rows : 每页显示的记录数
     * @return
     */
    PageResultVo listRoleByPage(Integer page, Integer rows);

    /**
     * 根据角色id更新角色的状态
     *
     * @param roleId
     * @param status
     * @return
     */
    Integer updateRoleById(Long roleId, Integer status);

    /**
     * 根据角色id删除角色
     *
     * @param roleId
     * @return
     */
    Boolean removeRoleById(Long roleId);

    /**
     * 根据角色id查询角色。附带该角色所拥有的菜单权限
     *
     * @param roleId
     * @return
     */
    SysRole getRoleWithMenuByRoleId(Long roleId);

    /**
     * 查询所有菜单角色列表
     *
     * @return
     */
    List<SysRole> listAllRole();
}
