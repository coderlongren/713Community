package com.community.manager.vo;

import com.community.manager.entity.ResourceType;
import com.community.manager.entity.User;

import java.util.List;

/**
 * @Author:王喜
 * @Description : 显示添加资源时的两个下拉框的信息
 * @Date: 2017/11/6 0006 23:26
 */
public class AddResourceVo {

    //定义User集合，便于查询出所有发布资源用户名字
    private List<User> userList;

    //定义ResourceType集合，便于查询出所有资源类别
    private List<ResourceType> resourceTypeList;

    public AddResourceVo() {
    }

    public AddResourceVo(List<User> userList, List<ResourceType> resourceTypeList) {
        this.userList = userList;
        this.resourceTypeList = resourceTypeList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<ResourceType> getResourceTypeList() {
        return resourceTypeList;
    }

    public void setResourceTypeList(List<ResourceType> resourceTypeList) {
        this.resourceTypeList = resourceTypeList;
    }
}
