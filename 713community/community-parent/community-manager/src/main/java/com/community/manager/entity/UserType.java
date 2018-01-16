package com.community.manager.entity;

import java.io.Serializable;

/**
 * @Author: xian
 * @Description: 用户等级实体类
 * @Date:create in 2017/10/31 20:04
 */
public class UserType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String typeName;

    @Override
    public String toString() {
        return "UserType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
