package com.community.manager.vo;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/16 19:16.
 */
public class CommentUserVo {

    private String username;
    private String userurl;

    public CommentUserVo() {

    }

    @Override
    public String toString() {
        return "CommentUserVo{" +
                "username='" + username + '\'' +
                ", userurl='" + userurl + '\'' +
                '}';
    }

    public CommentUserVo(String username, String userurl) {
        this.username = username;
        this.userurl = userurl;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserurl() {
        return userurl;
    }

    public void setUserurl(String userurl) {
        this.userurl = userurl;
    }
}
