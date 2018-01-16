package com.community.resource.service.impl;

import com.community.common.service.ApiService;
import com.community.resource.dao.IndexDao;
import com.community.resource.entity.Image;
import com.community.resource.entity.ResourceType;
import com.community.resource.service.IndexService;
import com.community.resource.vo.IndexVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:王喜
 * @Description :资源首页接口实现类
 * @Date: 2017/12/19 0019 9:50
 */
@Service
public class IndexServiceImpl implements IndexService{


    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ApiService apiService;

    @Autowired
    private IndexDao indexDao;

    @Value("${MANAGER_URL}")
    private String MANAGER_URL;

    @Value("${INDEX_IMAGE_URL}")
    private String INDEX_IMAGE_URL;

    /**
     * 访问资源共享首页数据
     *
     * @return
     */
    @Override
    public IndexVo listResourceIndexInfo() throws IOException{
        //获取资源首页轮播图
        List<Image> indexTops = listResourceIndexImage();

        //获取资源首页下面资源类型介绍信息
        List<ResourceType> resourceTypes = indexDao.listResourceTypeInfo();

        IndexVo indexVo = new IndexVo(indexTops,resourceTypes);

        return indexVo;
    }



    //自定义方法  获取资源首页轮播图
    public List<Image> listResourceIndexImage() throws IOException{
        String url_image = MANAGER_URL + INDEX_IMAGE_URL + "?typeIds=" + 1;

        String jsonData_images = apiService.doGet(url_image);
        if (null == jsonData_images) {
            return null;
        }

        //将json格式的数据解析成对应的集合实体
        List<Image> images = MAPPER.readValue(jsonData_images, new TypeReference<List<Image>>() {});
        //用stream流来对images进行过滤分类存储
        List<Image> indexTops = images.stream()
                .filter(e -> e.getTypeId() == 1L)
                .filter(e -> e.getStatus() == 1)
                .collect(Collectors.toList());

        return indexTops;
    }
}
