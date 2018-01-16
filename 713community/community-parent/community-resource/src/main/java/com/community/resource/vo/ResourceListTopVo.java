package com.community.resource.vo;

import com.community.resource.entity.ResourceType;

/**
 * @Author:王喜
 * @Description :资源列表首部Vo
 * @Date: 2017/12/19 0019 18:26
 */
public class ResourceListTopVo {

    private String typename;

    private Integer total;

    private Integer totalFlag;

    private Integer rank;



    public ResourceListTopVo() {
    }

    public ResourceListTopVo(String typename, Integer total,
                             Integer totalFlag, Integer rank
                             ) {
        this.typename = typename;
        this.total = total;
        this.totalFlag = totalFlag;
        this.rank = rank;
    }

    public Integer getTotalFlag() {
        return totalFlag;
    }

    public void setTotalFlag(Integer totalFlag) {
        this.totalFlag = totalFlag;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
