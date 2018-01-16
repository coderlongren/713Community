package com.community.resource.vo;

/**
 * @Author:王喜
 * @Description :存储用户的评论
 * @Date: 2017/11/18 0018 15:35
 */
public class ResourceCommentUserVo {

    private String username;

    private String userurl;


    public ResourceCommentUserVo(String username, String userurl) {
        this.username = username;
        this.userurl = userurl;
    }

    public ResourceCommentUserVo() {
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
