package com.community.manager.enumeration;

/**
 * @Author: xian
 * @Description: 相应码信息枚举类
 * @Date:create in 2017/10/22 14:25
 */
public enum ResponseCodeEnum {
    FIND_SUCCESS(200, ""),
    ADD_success(201, "添加成功"),
    NOT_FOUND(404, "请求资源不存在"),
    UPDATE_SUCCESS(204, "更新成功"),
    DELETE_SUCCESS(204, "删除成功"),
    INNER_SERVER_ERROR(500, "操作失败！服务器出错！");

    private Integer code;

    private String msg;

    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
