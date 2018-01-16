package com.community.web.vo;

/**
 * @Author:王喜
 * @Description 存储用户联系方式Vo
 * @Date: 2017/11/27 0027 19:56
 */
public class UserContactVo {

    //联系方式类型
    private String type;

    //联系方式值
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
