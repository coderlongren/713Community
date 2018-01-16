package com.community.manager.service.impl;

import com.community.manager.dao.UserRankDao;
import com.community.manager.entity.UserRank;
import com.community.manager.service.UserRankService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/10/31 20:13
 */
@Service
public class UserRankServiceImpl implements UserRankService {

    @Autowired
    private UserRankDao userRankDao;
    @Override
    public PageResultVo viewUserRankByPage(Integer page) {

        if (page<=0||page==null){
            page=1;
        }
        int rows = 10;
        PageHelper.startPage(page,rows);

        List<UserRank> list = userRankDao.listAll();
        PageInfo<UserRank> pageInfo = new PageInfo<>(list);


        return new PageResultVo(pageInfo.getTotal(),pageInfo.getList(),pageInfo.getPages());
    }

    @Override
    public Boolean removeUserById(Long id) {
        return userRankDao.deleteById(id)==1;
    }

    @Override
    public UserRank getUserRankById(Long id) {
        return userRankDao.getById(id);
    }

    @Override
    public Integer insertUserRank(UserRank userRank) {
        userRank.setCreateTime(new Date());
        userRank.setUpdateTime(new Date());
        return userRankDao.insert(userRank);
    }

    @Override
    public Boolean updateUserRankById(Long id, UserRank userRank) {
        userRank.setId(id);
        userRank.setUpdateTime(new Date());
        return userRankDao.update(userRank)==1;
    }

    @Override
    public PageResultVo listUserRankWithSearch(String searchVal, Integer page, Integer rows) {

        if(page<=0||null==page){
            page=1;
        }
        PageHelper.startPage(page,rows);

        String val = new StringBuilder("%").append(searchVal).append("%").toString();
        List<UserRank> list = userRankDao.listUserRankWithSearch(val);
        PageInfo<UserRank> pageInfo = new PageInfo<>(list);

        return new PageResultVo(pageInfo.getTotal(),pageInfo.getList(),pageInfo.getPages());
    }

    @Override
    public List<UserRank> listAllUserRank() {
        return userRankDao.listAll();
    }
}
