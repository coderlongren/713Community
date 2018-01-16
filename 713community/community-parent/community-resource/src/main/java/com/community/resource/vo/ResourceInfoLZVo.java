package com.community.resource.vo;

import com.community.resource.entity.Resource;

/**
 * @Author:王喜
 * @Description :资源信息楼主Vo
 * @Date: 2017/12/25 0025 9:51
 */
public class ResourceInfoLZVo {

    private Resource resource;

    public ResourceInfoLZVo() {
    }

    public ResourceInfoLZVo(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "ResourceInfoLZVo{" +
                "resource=" + resource +
                '}';
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
