package com.community.manager.service;

import com.community.manager.entity.UserType;
import com.community.manager.vo.PageResultVo;

import java.util.List;

/**
 * @Author: xian
 * @Description: 用户类别service接口
 * @Date:create in 2017/10/31 20:12
 */
public interface UserTypeService {

    /**
     * 分页查询所有userType
     */
     PageResultVo viewUserTypeByPage(Integer page);

     //根据id删除userType
     Boolean removeUserById(Long id);

     //根据id查询单个userType
     UserType getUserTypeById(Long id);

     //新增用户类型
     Integer insertUserType(UserType userType);

     //根据id更新用户类型
     Boolean updateUserTypeById(Long id,UserType userType);

     PageResultVo listUserTypeWithSearch(String searchVal,Integer page,Integer rows);

     List<UserType> listAllUserType();
}
