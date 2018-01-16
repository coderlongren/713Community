package com.community.resource.vo;

import com.community.resource.entity.Image;
import com.community.resource.entity.ResourceType;

import java.util.List;

/**
 * @Author:王喜
 * @Description :资源首页的Vo
 * @Date: 2017/12/19 0019 10:07
 */
public class IndexVo {

    private List<Image> indexTops;

    private List<ResourceType> resourceTypes;


    public IndexVo() {
    }

    public IndexVo(List<Image> indexTops, List<ResourceType> resourceTypes) {
        this.indexTops = indexTops;
        this.resourceTypes = resourceTypes;
    }

    public List<Image> getIndexTops() {
        return indexTops;
    }

    public void setIndexTops(List<Image> indexTops) {
        this.indexTops = indexTops;
    }

    public List<ResourceType> getResourceTypes() {
        return resourceTypes;
    }

    public void setResourceTypes(List<ResourceType> resourceTypes) {
        this.resourceTypes = resourceTypes;
    }
}
