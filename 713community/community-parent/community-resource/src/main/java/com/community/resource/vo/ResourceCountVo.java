package com.community.resource.vo;

/**
 * @Author:王喜
 * @Description :资源数量Vo
 * @Date: 2017/12/20 0020 11:41
 */
public class ResourceCountVo {

    private Integer count;

    private String name;

    public ResourceCountVo() {
    }

    public ResourceCountVo(Integer count, String name) {
        this.count = count;
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
