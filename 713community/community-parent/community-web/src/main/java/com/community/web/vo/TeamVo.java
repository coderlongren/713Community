package com.community.web.vo;

import com.community.web.bean.User;

import java.util.List;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/26 10:48.
 */
public class TeamVo {

    private List<User> users;

    public TeamVo() {
    }

    @Override
    public String toString() {
        return "TeamVo{" +
                "users=" + users +
                '}';
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
