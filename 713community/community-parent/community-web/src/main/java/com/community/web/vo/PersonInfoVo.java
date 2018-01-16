package com.community.web.vo;

import com.community.web.bean.User;

/**
 * @Author:王喜
 * @Description :个人中心Vo
 * @Date: 2017/11/27 0027 19:16
 */

public class PersonInfoVo {

    private User user;

    private UserContentVo userContentVo;

    public PersonInfoVo() {
    }

    public PersonInfoVo(User user, UserContentVo userContentVo) {
        this.user = user;
        this.userContentVo = userContentVo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserContentVo getUserContent() {
        return userContentVo;
    }

    public void setUserContent(UserContentVo userContentVo) {
        this.userContentVo = userContentVo;
    }
}

