package com.community.manager.service.impl;

import com.community.manager.dao.SysUserDao;
import com.community.manager.dao.SysUserRoleDao;
import com.community.manager.entity.SysUser;
import com.community.manager.entity.SysUserRole;
import com.community.manager.service.SysUserService;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.UserRoleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/10/18 11:59
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    //将需要使用的数据操作对应的dao实体对象注入
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    public SysUser getByUserName(String userName) {
        return sysUserDao.getByUserName(userName);
    }

    /**
     * 分页查询系统管理员用户
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listAllUserByPage(Integer page, Integer rows) {
        //为了提升系统的健壮性，先判断page是否为空，为空则设一个默认值1
        if (page == null || page <= 0) {
            page = 1;
        }

        //初始化分页查询的配置
        PageHelper.startPage(page, rows);

        /*该行代码本身不是一个分页查询，就是一个普通的list查询，但只要紧跟着上一行代码
         *PageHelper.startPage(page, rows)的，就会是一个分页查询
         */
        List<SysUser> list = sysUserDao.listAll(); //TODO 查询所有管理员用户时不应该把自己查出来

        //将分页查出来的list封装到PageInfo
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);

        //返回PageresultVo对象，该返回值类型注意与上层调用者controller层的接受类型对应
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 新增管理员用户
     *
     * @param user
     * @param roleIds
     * @return
     */
    @Override
    public Boolean insertUser(SysUser user, String roleIds) {
        //先将角色id串切分开，转为一个数组
        String[] roleId = roleIds.split(",");

        //先往sys_user表插入数据
        //做一些必要的数据初始化
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setCreateUserId(1L);
        // TODO 设置管理员创建者id
        user.setStatus(2);//2表示停用

        //返回值为插入成功的记录数，1
        int result = sysUserDao.insert(user);

        //当管理员用户插入成功后，才能往管理员用户-角色映射表插入数据
        SysUserRole sysUserRole = new SysUserRole();
        for (int i = 0; i < roleId.length; i++) {
            sysUserRole.setRoleId(Long.valueOf(roleId[i]));// 对String类型的id值转为Long,否则数据类型不匹配，无法赋值
            sysUserRole.setSysUserId(user.getId());
            sysUserRoleDao.insert(sysUserRole); // TODO 使用mybatis的批量删除
        }
        return result == 1;
    }

    /**
     * 根据用户id查询用户信息，并附带角色列表数据
     *
     * @param userId
     * @return
     */
    @Override
    public SysUser getByUserIdWithRoles(Long userId) {

        return sysUserDao.getByIdWithRoles(userId);
    }

    /**
     * 根据管理员id更新管理员数据
     *
     * @param userId
     * @param user
     * @return
     */
    @Override
    public Boolean updateSysUserById(Long userId, SysUser user) {
        user.setUpdateTime(new Date());
        user.setId(userId);
        return sysUserDao.update(user) == 1;
    }

    /**
     * 根据id删除管理员用户
     *
     * @param userId
     * @return
     */
    @Override
    public Boolean removeSysUserById(Long userId) {
        return sysUserDao.deleteById(userId) == 1;
    }

    /**
     * 根据管理员名称模糊搜索用户
     *
     * @param username
     * @param rows
     * @param page
     * @return
     */
    @Override
    public  PageResultVo searchByUserName(String username, Integer rows, Integer page) {
        String name = new StringBuilder("%").append(username).append("%").toString();

        if (rows == null) {
            rows = 10;
        }

        if (page <= 0) {
            page = 1;
        }

        PageHelper.startPage(page, rows);
        List<SysUser> list = sysUserDao.listUserByName(name);

        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }


}
