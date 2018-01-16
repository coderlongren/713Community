package com.community.manager.vo;

import java.util.List;

/**
 * @Author: xian
 * @Description: 响应信息对象
 * @Date:create in 2017/10/22 14:02
 */
public class MessageVo {

    private Integer code;

    private String message;

    private List<?> data;

    public MessageVo() {}

    public MessageVo(Integer code, String message, List<?> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public MessageVo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
