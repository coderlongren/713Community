package com.community.manager.service.impl;

import com.community.manager.dao.UserDao;
import com.community.manager.dao.UserRankDao;
import com.community.manager.dao.UserTypeDao;
import com.community.manager.entity.User;
import com.community.manager.entity.UserRank;
import com.community.manager.entity.UserType;
import com.community.manager.service.UserService;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.UserEditDataVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: xian
 * @Description: 网站用户service实现类
 * @Date:create in 2017/10/31 16:44
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserTypeDao userTypeDao;

    @Autowired
    private UserRankDao userRankDao;

    /**
     * 分页查询网站用户
     *
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAllUserByPage(Integer page) {
        if (page <= 0 || null == page) {
            page = 1;
        }
        int rows = 10;

        /*
         * page:表示下一页
         * rows:表示每一页显示的记录数
         */
        PageHelper.startPage(page, rows);
        List<User> list = userDao.listAll();

        PageInfo<User> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 根据id查询用户
     *
     * @param userId
     * @return
     */
    @Override
    public UserEditDataVo getUserById(Long userId) {
        User user = userDao.getById(userId);
        List<UserType> types = userTypeDao.listAll();
        List<UserRank> ranks = userRankDao.listAll();
        return new UserEditDataVo(user, types, ranks);
    }

    /**
     * 根据用户id更新用户
     *
     * @param userId
     * @param user
     * @return
     */
    @Override
    public Boolean updateUserById(Long userId, User user) {
        user.setUpdateTime(new Date());
        user.setId(userId);
        return userDao.update(user) == 1;
    }

    /**
     *
     * 根据id删除用户
     *
     * @param userId
     * @return
     */
    @Override
    public Boolean deleteUserById(Long userId) {
        return userDao.deleteById(userId)==1;
    }

    /**
     * 根据用户名模糊查询用户
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listUserWithSearch(String searchVal, Integer page, Integer rows) {

        if(page<=0||null==page){
            page=1;
        }
        PageHelper.startPage(page,rows);

        String val = new StringBuffer("%").append(searchVal).append("%").toString();
        List<User> list = userDao.listUserWithSearch(val);
        PageInfo<User> pageInfo = new PageInfo<>(list);

        return new PageResultVo(pageInfo.getTotal(),pageInfo.getList(),pageInfo.getPages());
    }

    /**
     *
     * 新增用户
     *
     * @param user
     * @return
     */

    @Override
    public Integer insertUser(User user) {
        user.setStatus(2);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userDao.insert(user);
    }

    /**
     * 根据Id查询用户
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserByUserId(Long userId) {

        User user = new User();

        user = userDao.getById(userId);

        return user;
    }

    @Override
    public List<User> listAllUser() {
        return userDao.listAll();
    }
}
