package com.community.manager.vo;

import com.community.manager.entity.User;
import com.community.manager.entity.UserRank;
import com.community.manager.entity.UserType;

import java.util.List;

/**
 * @Author: xian
 * @Description:用户编辑页面所需要的传输对象
 * @Date:create in 2017/10/31 21:40
 */
public class UserEditDataVo {

    private User user;

    private List<UserType> types;

    private List<UserRank> ranks;

    public UserEditDataVo() {
    }

    public UserEditDataVo(User user, List<UserType> types, List<UserRank> ranks) {
        this.user = user;
        this.types = types;
        this.ranks = ranks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserType> getTypes() {
        return types;
    }

    public void setTypes(List<UserType> types) {
        this.types = types;
    }

    public List<UserRank> getRanks() {
        return ranks;
    }

    public void setRanks(List<UserRank> ranks) {
        this.ranks = ranks;
    }
}
