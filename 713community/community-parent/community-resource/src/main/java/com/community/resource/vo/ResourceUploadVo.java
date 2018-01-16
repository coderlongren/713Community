package com.community.resource.vo;

/**
 * @Author:王喜
 * @Description :资源上传vo
 * @Date: 2018/1/11 0011 23:38
 */
public class ResourceUploadVo {

    private String resourceTitle;

    private String resourceAuthor;

    private String resourceContent;

    private Long resourceTypeId;

    public ResourceUploadVo(String resourceTitle, String resourceAuthor, String resourceContent, Long resourceTypeId) {
        this.resourceTitle = resourceTitle;
        this.resourceAuthor = resourceAuthor;
        this.resourceContent = resourceContent;
        this.resourceTypeId = resourceTypeId;
    }

    public ResourceUploadVo() {
    }

    public Long getResourceTypeId() {
        return resourceTypeId;
    }

    public void setResourceTypeId(Long resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }

    public String getResourceTitle() {
        return resourceTitle;
    }

    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    public String getResourceAuthor() {
        return resourceAuthor;
    }

    public void setResourceAuthor(String resourceAuthor) {
        this.resourceAuthor = resourceAuthor;
    }

    public String getResourceContent() {
        return resourceContent;
    }

    public void setResourceContent(String resourceContent) {
        this.resourceContent = resourceContent;
    }
}
