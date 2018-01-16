package com.community.web.vo;

import com.community.web.bean.HomePage;
import com.community.web.bean.Image;

import java.util.List;

/**
 * @Author: xian
 * @Description:首页内容传输对象
 * @Date:create in 2017/11/16 9:55
 */
public class IndexVo {

    private List<Image> indexTops;

    private List<Image> indexMiddles;

    private List<Image> indexBottoms;

    private List<HomePage> indexNavs;


    public IndexVo(List<Image> indexTops, List<Image> indexMiddles, List<Image> indexBottoms, List<HomePage> indexNavs) {
        this.indexTops = indexTops;
        this.indexMiddles = indexMiddles;
        this.indexBottoms = indexBottoms;
        this.indexNavs = indexNavs;
    }

    public List<Image> getIndexTops() {
        return indexTops;
    }

    public void setIndexTops(List<Image> indexTops) {
        this.indexTops = indexTops;
    }

    public List<Image> getIndexMiddles() {
        return indexMiddles;
    }

    public void setIndexMiddles(List<Image> indexMiddles) {
        this.indexMiddles = indexMiddles;
    }

    public List<Image> getIndexBottoms() {
        return indexBottoms;
    }

    public void setIndexBottoms(List<Image> indexBottoms) {
        this.indexBottoms = indexBottoms;
    }

    public List<HomePage> getIndexNavs() {
        return indexNavs;
    }

    public void setIndexNavs(List<HomePage> indexNavs) {
        this.indexNavs = indexNavs;
    }
}
