package com.community.manager.service.impl;

import com.community.manager.dao.UserTypeDao;
import com.community.manager.entity.UserType;
import com.community.manager.service.UserTypeService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/10/31 20:13
 */
@Service
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private UserTypeDao userTypeDao;

    /**
     * 分页查询usertype
     * @param page
     * @return
     */
    @Override
    public PageResultVo viewUserTypeByPage(Integer page) {

        if(page<=0||null==page){
            page=1;
        }
        int rows = 10;

        PageHelper.startPage(page,rows);
        List<UserType> list = userTypeDao.listAll();

        PageInfo<UserType> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(),pageInfo.getList(),pageInfo.getPages());

        //return null;
    }

    /**
     * 根据id删除usertype
     * @param id
     * @return
     */
    @Override
    public Boolean removeUserById(Long id) {

        int result = userTypeDao.deleteById(id) ;

        return result==1;
    }

    /**
     * 根据id查找usertype
     * @param id
     * @return
     */
    @Override
    public UserType getUserTypeById(Long id) {
        return userTypeDao.getById(id);
    }

    /**
     * 新增用户类型
     * @param userType
     * @return Integer
     */
    @Override
    public Integer insertUserType(UserType userType) {
        int res;
        res = userTypeDao.insert(userType);
        return res;
    }

    /**
     * 根据id更新用户类型
     * @param id
     * @param userType
     * @return
     */
    @Override
    public Boolean updateUserTypeById(Long id, UserType userType) {
        userType.setId(id);
        return userTypeDao.update(userType)==1;
    }

    /**
     * 模糊查询用户类型
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listUserTypeWithSearch(String searchVal, Integer page, Integer rows) {

        if(page<=0||null==page){
            page=1;
        }
        PageHelper.startPage(page,rows);
        String value = new StringBuilder("%").append(searchVal).append("%").toString();
        List<UserType> list = userTypeDao.listUserTypeWithSearch(value);
        PageInfo<UserType> pageInfo = new PageInfo<>(list);

        return new PageResultVo(pageInfo.getTotal(),pageInfo.getList(),pageInfo.getPages());
    }

    @Override
    public List<UserType> listAllUserType() {
        return userTypeDao.listAll();
    }
}
