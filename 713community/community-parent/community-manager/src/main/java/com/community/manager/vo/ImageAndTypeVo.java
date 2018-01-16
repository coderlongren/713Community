package com.community.manager.vo;

import com.community.manager.entity.Image;
import com.community.manager.entity.ImageType;

import java.util.List;

/**
 * @Author: xian
 * @Description:图片和图片类型的传输对象
 * @Date:create in 2017/11/6 9:53
 */
public class ImageAndTypeVo {

    private Image image;

    private List<ImageType> imageTypes;


    public ImageAndTypeVo(Image image, List<ImageType> imageTypes) {
        this.image = image;
        this.imageTypes = imageTypes;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<ImageType> getImageTypes() {
        return imageTypes;
    }

    public void setImageTypes(List<ImageType> imageTypes) {
        this.imageTypes = imageTypes;
    }
}
