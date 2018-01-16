package com.community.manager.service;

import com.community.manager.entity.User;
import com.community.manager.entity.UserRank;
import com.community.manager.vo.PageResultVo;

import java.util.List;

/**
 * @Author: xian
 * @Description:用户等级service接口
 * @Date:create in 2017/10/31 20:12
 */
public interface UserRankService {

    //分页查询
    PageResultVo viewUserRankByPage(Integer page);

    //根据id删除用户等级
    Boolean removeUserById(Long id);

    //根据id查询用户等级
    UserRank getUserRankById(Long id);

    //新增用户等级
    Integer insertUserRank(UserRank userRank);

    //更新用户等级
    Boolean updateUserRankById(Long id,UserRank userRank);

    //模糊查询用户等级
    PageResultVo listUserRankWithSearch(String searchVal,Integer page,Integer rows);

    List<UserRank> listAllUserRank();
}
