package com.community.web.service.impl;

import com.community.common.service.ApiService;
import com.community.web.bean.RecruitInfo;
import com.community.web.service.RecruitService;
import com.community.web.vo.RecruitAndImageVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/27 11:10
 */
@Service
public class RecruitServiceImpl implements RecruitService {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ApiService apiService;

    @Value("${MANAGER_URL}")
    private String MANAGER_URL;

    @Value("${INDEX_RECRUIT_URL}")
    private String INDEX_RECRUIT_URL;

    @Value("${RECRUIT_TYPE_IDS}")
    private String RECRUIT_TYPE_IDS;

    @Value("${RECRUIT_INFO}")
    private String RECRUIT_INFO;

    /**
     * 访问加入我们首页
     *
     * @return
     */
    @Override
    public RecruitAndImageVo getImagesAndRecruitTypesVo() throws IOException{
        String url = MANAGER_URL + INDEX_RECRUIT_URL + "?typeIds=" + RECRUIT_TYPE_IDS;

        String jsonData = apiService.doGet(url);

        if (jsonData == null) {
            return null;
        }

        RecruitAndImageVo recruitAndImageVo = MAPPER.readValue(jsonData, new TypeReference<RecruitAndImageVo>() {});

        return recruitAndImageVo;
    }

    /**
     * 根据id获取招新详情
     *
     * @param infoId
     * @return
     * @throws IOException
     */
    @Override
    public RecruitInfo getRecruitInfoVo(Long infoId) throws IOException {

        String url = MANAGER_URL + RECRUIT_INFO + infoId ;

        String jsonData = apiService.doGet(url);

        if (jsonData == null) {
            return null;
        }
        //将从manager子系统获取过来的jsondata数据转换成能够解析json格式的JsonNode对象
        JsonNode jsonNode = MAPPER.readTree(jsonData);
        //用JsonNode取出recruitInfo对应的值
        //System.out.println(jsonNode.get("recruitTypes"));
        JsonNode infoNode = jsonNode.get("recruitInfo");
        RecruitInfo recruitInfo = MAPPER.readValue(infoNode.toString(), new TypeReference<RecruitInfo>() {});

        return recruitInfo;
    }
}
