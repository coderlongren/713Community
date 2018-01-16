package com.community.manager.service;

import com.community.manager.entity.User;
import com.community.manager.entity.UserRank;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.UserEditDataVo;

import java.util.List;

/**
 * @Author: xian
 * @Description: 网站用户service接口
 * @Date:create in 2017/10/31 16:43
 */
public interface UserService {

    /**
     * 分页查询网站用户
     *
     * @param page
     * @return
     */
    PageResultVo listAllUserByPage(Integer page);

    /**
     * 根据用户id查询用户
     *
     * @param userId
     * @return
     */
    UserEditDataVo getUserById(Long userId);

    /**
     * 根据用户id更新用户
     *
     * @param userId
     * @param user
     * @return
     */
    Boolean updateUserById(Long userId, User user);

    /**
     *根据id删除用户
     *
     * @param userId
     * @return
     */
    Boolean deleteUserById(Long userId);

    /**
     *
     * 根据用户名称模糊查询用户
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listUserWithSearch(String searchVal,Integer page,Integer rows);

    /**
     * 新增用户
     * @param user
     * @return
     */
    Integer insertUser(User user);

    /**
     * 根据Id查询用户
     *
     * @param userId
     * @return
     */
    User getUserByUserId(Long userId);

    List<User>  listAllUser();

}
