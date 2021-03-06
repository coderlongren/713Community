package com.community.manager.vo;

/**
 * @Author: xian
 * @Description: 图片上传相关传输对象
 * @Date:create in 2017/11/4 19:54
 */
public class PicUploadResultVo {
    private Integer error;

    private String url;

    private String width;

    private String height;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
