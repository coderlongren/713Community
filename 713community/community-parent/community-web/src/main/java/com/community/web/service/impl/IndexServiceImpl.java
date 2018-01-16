package com.community.web.service.impl;

import com.community.common.service.ApiService;
import com.community.web.bean.HomePage;
import com.community.web.bean.Image;
import com.community.web.service.IndexService;
import com.community.web.vo.IndexVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/11/16 9:51
 */
@Service
public class IndexServiceImpl implements IndexService {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ApiService apiService;

    @Value("${MANAGER_URL}")
    private String MANAGER_URL;

    @Value("${INDEX_IMAGE_URL}")
    private String INDEX_IMAGE_URL;

    @Value("${TYPE_IDS}")
    private String TYPE_IDS;

    @Value("${INDEX_NAV_URL}")
    private String INDEX_NAV_URL;

    /**
     * 访问首页，获取图片
     *
     * @return
     */
    public IndexVo listImageByTypeIds() throws IOException {
        String url_image = MANAGER_URL + INDEX_IMAGE_URL + "?typeIds=" + TYPE_IDS;

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
         
        List<Image> indexMiddles = images.stream()
                .filter(e -> (e.getTypeId() == 4L && e.getStatus() == 1))
                .collect(Collectors.toList());

        List<Image> indexBottoms = images.stream()
                .filter(e -> (e.getTypeId() == 2L && e.getStatus() == 1))
                .collect(Collectors.toList());

        IndexVo indexVo = new IndexVo(indexTops, indexMiddles, indexBottoms, null);

        return indexVo;
    }

    /**
     * 获取首页导航栏
     *
     * @return
     * @throws IOException
     */
    @Override
    public IndexVo listIndexNav() throws IOException {
        String url_indexNav = MANAGER_URL + INDEX_NAV_URL;
        String jsonData_indexNav = apiService.doGet(url_indexNav);
        if (null == jsonData_indexNav) {
            return null;
        }
        JsonNode jsonNode = MAPPER.readTree(jsonData_indexNav);
        JsonNode navNodes = jsonNode.get("rows");
        List<HomePage> indexNavs = MAPPER.readValue(navNodes.toString(), new TypeReference<List<HomePage>>() {});
        IndexVo indexVo = new IndexVo(null, null, null, indexNavs);
        return indexVo;
    }
}
