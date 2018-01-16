package com.community.manager.service;

import com.community.manager.entity.SysUser;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.UserRoleVo;

import java.util.List;

/**
 * @Author: xian
 * @Description: 系统用户逻辑处理层
 * @Date:create in 2017/10/18 11:58
 */
public interface SysUserService {

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    SysUser getByUserName (String userName);

    /**
     * 分页查询系统管理员用户
     *
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listAllUserByPage(Integer page, Integer rows);

    /**
     * 新增管理员用户
     *
     * @param user
     * @param roleIds
     * @return : 注意-所有的返回值类型，均不用基本类型，均转为基本类型对应的包装类型
     */
    Boolean insertUser(SysUser user, String roleIds);

    /**
     * 根据用户id查询用户信息，并附带角色列表数据
     *
     * @param userId
     * @return
     */
    SysUser getByUserIdWithRoles(Long userId);

    /**
     * 根据管理员id更新管理员数据
     *
     * @param userId
     * @param user
     * @return
     */
    Boolean updateSysUserById(Long userId, SysUser user);

    /**
     * 根据id删除管理员用户
     *
     * @param userId
     * @return
     */
    Boolean removeSysUserById(Long userId);

    /**
     * 根据管理员名称模糊搜索用户
     *
     * @param username
     * @param rows
     * @param page
     * @return
     */
    PageResultVo searchByUserName(String username, Integer rows, Integer page);
}
