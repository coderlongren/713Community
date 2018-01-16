package com.community.resource.service;

import com.community.resource.entity.User;
import com.community.resource.vo.PageResultVo;
import com.community.resource.vo.ResourceListTopVo;
import com.community.resource.vo.ResourceUploadVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author:王喜
 * @Description :资源列表页面Service
 * @Date: 2017/12/19 0019 18:19
 */
public interface ResourceListService {
    /**
     * 根据id查询资源列表首部
     * @param resourceTypeId
     * @return
     * @throws IOException
     */
    ResourceListTopVo listResourceListTopInfoById(Long resourceTypeId) throws IOException;

    /**
     * 分页查询资源列表信息
     *
     * @param page
     * @return
     */
    PageResultVo listAllResourceByPage(Long resourceTypeId,Integer page) ;
    /**
     * 上传zip压缩文件
     *
     * @param file
     * @return
     */
    Boolean uploadZipFile(MultipartFile file, ResourceUploadVo resourceUploadVo) throws IOException;

    /**
     * 富文本编辑器的图片上传
     *
     * @param pic
     * @return
     * @throws IOException
     */
    String uploadPicture(MultipartFile pic) throws IOException;

    /**
     * 根据session中的用户名查询用户的信息
     *
     * @param username
     * @return
     */
    User findUserInfoByUsername(String username);
}
