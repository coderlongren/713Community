package com.community.manager.service.impl;

import com.community.manager.dao.ImageDao;
import com.community.manager.dao.ImageTypeDao;
import com.community.manager.entity.Image;
import com.community.manager.entity.ImageType;
import com.community.manager.service.ImageService;
import com.community.manager.vo.ImageAndTypeVo;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.PicUploadResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/11/4 19:56
 */
@Service
public class ImageServiceImpl implements ImageService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};

    @Value("${UPLOAD_PATH}")
    private String UPLOAD_PATH;

    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Autowired
    private ImageDao imageDao;
    @Autowired
    private ImageTypeDao imageTypeDao;

    /**
     * 上传图片
     *
     * @param pic
     * @param image
     * @return 图片的src路径
     * @throws IOException
     */
    @Override
    public String uploadPicture(MultipartFile pic, Image image) throws IOException {

        // 校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(pic.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }

        // 封装Result对象，并且将文件的byte数组放置到result对象中
        PicUploadResultVo fileUploadResult = new PicUploadResultVo();

        // 状态
        fileUploadResult.setError(isLegal ? 0 : 1);

        // 文件新路径
        String filePath = getFilePath(pic.getOriginalFilename());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Pic file upload .[{}] to [{}] .", pic.getOriginalFilename(), filePath);
        }

        // 生成图片的绝对引用地址
        String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath, UPLOAD_PATH),
                "\\", "/");
        String imgSrc = IMAGE_BASE_URL + picUrl;
        fileUploadResult.setUrl(imgSrc);


        File newFile = new File(filePath);

        // 写文件到磁盘
        pic.transferTo(newFile);

        // 校验图片是否合法
        isLegal = false;
        try {
            BufferedImage img = ImageIO.read(newFile);
            if (img != null) {
                fileUploadResult.setWidth(img.getWidth() + "");
                fileUploadResult.setHeight(img.getHeight() + "");
                isLegal = true;
            }
        } catch (IOException e) {
        }

        // 状态
        fileUploadResult.setError(isLegal ? 0 : 1);

        if (!isLegal) {
            // 不合法，将磁盘上的文件删除
            newFile.delete();
        }

        //更新图片表中的数据

        if (image != null) {
            image.setUpdateTime(new Date());
            image.setImageName(pic.getOriginalFilename());
            image.setUrl(IMAGE_BASE_URL + picUrl);

            imageDao.update(image);
        }

        return imgSrc;

    }

    /**
     * 添加图片数据
     *
     * @param image
     * @return
     */
    @Override
    public Long insertImage(Image image) {
        //对于没有从前台传过来的，在此设置默认值
        image.setCreateTime(new Date());
        image.setUpdateTime(new Date());
        int result = imageDao.insert(image);
        Long imageId = null;
        if (result == 1) {
            //插入成功，返回插入后的数据id
            imageId = image.getId();
        }
        return imageId;
    }

    /**
     * 根据id查询图片
     *
     * @param imageIdOnUpload
     * @return
     */
    @Override
    public Image getImageById(Long imageIdOnUpload) {
        return imageDao.getById(imageIdOnUpload);
    }

    /**
     * 分页查询图片数据
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listAllByPage(Integer page, Integer rows) {
        if (null == page || page <= 0) {
            page = 1;
        }

        PageHelper.startPage(page, rows);
        List<Image> list = imageDao.listAll();

        PageInfo<Image> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 根据id查询图片数据，附带图片类型信息
     *
     * @param imageId
     * @return
     */
    @Override
    public ImageAndTypeVo getByIdWithType(Long imageId) {
        Image image = imageDao.getById(imageId);
        List<ImageType> imageTypes = imageTypeDao.listAll();
        return new ImageAndTypeVo(image, imageTypes);
    }

    /**
     * 更新图片信息
     *
     * @param image
     * @param imageId
     * @return
     */
    @Override
    public Boolean updateImage(Long imageId, Image image) {
        image.setId(imageId);
        image.setUpdateTime(new Date());
        return imageDao.update(image) == 1;
    }

    /**
     * 根据id删除图片信息
     *
     * @param imageId
     * @return
     */
    @Override
    public Boolean removeImage(Long imageId) {
        return imageDao.deleteById(imageId) == 1;
    }

    /**
     * 根据图片类型查询图片列表
     *
     * @param typeId
     * @return
     */
    @Override
    public List<Image> listImageByType(Long typeId) {
        return imageDao.listImageByType(typeId);
    }

    /**
     * 根据图片类型id集合查询图片列表
     *
     * @param typeIds
     * @return
     */
    @Override
    public List<Image> listImageByTypeIds(String typeIds) {

        if (null == typeIds || "" == typeIds) {
            return null;
        }
        String[] ids = typeIds.split(",");
        List<Long> listId = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            listId.add(Long.valueOf(ids[i]));
        }
        return imageDao.listByTypeIds(listId);
    }

    /**
     * 分页搜索查询
     *
     * @param page
     * @param rows
     * @param param
     * @param value
     * @return
     */
    @Override
    public PageResultVo listSearchImageByPage(Integer page, Integer rows, String param, String value) {
        if (null == page || page < 1) {
            page = 1;
        }
        String val = new StringBuilder("%").append(value).append("%").toString();
        PageHelper.startPage(page, rows);
        List<Image> list = imageDao.listBySearch(param, val);
        PageInfo<Image> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 生成图片名称和路径
     *
     * @param sourceFileName
     * @return
     */
    private String getFilePath(String sourceFileName) {
        String baseFolder = UPLOAD_PATH + File.separator + "images";
        Date nowDate = new Date();
        // yyyy/MM/dd
        String fileFolder = baseFolder + File.separator + new DateTime(nowDate).toString("yyyy") + File.separator + new DateTime(nowDate).toString("MM") + File.separator
                + new DateTime(nowDate).toString("dd");
        File file = new File(fileFolder);
        if (!file.isDirectory()) {
            // 如果目录不存在，则创建目录
            file.mkdirs();
        }
        // 生成新的文件名
        String fileName = new DateTime(nowDate).toString("yyyyMMddhhmmssSSSS") + RandomUtils.nextInt(100, 9999) + "." + sourceFileName;
        return fileFolder + File.separator + fileName;
    }
}
