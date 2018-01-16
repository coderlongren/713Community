package com.community.web.service.impl;

import com.community.common.service.ApiService;
import com.community.web.bean.User;
import com.community.web.bean.UserContent;
import com.community.web.service.PersonInfoService;
import com.community.web.vo.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author:王喜
 * @Description :
 * @Date: 2017/11/28 0028 16:05
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ApiService apiService;

    @Value("${MANAGER_URL}")
    private String MANAGER_URL;

    @Value("${USERCONTENT_URL}")
    private String USERCONTENT_URL;

    @Value("${USER_URL}")
    private String USER_URL;

    /**
     * 得到个人介绍信息
     *
     * @return
     */
    @Override
    public PersonInfoVo getPersonInfoInfo(Long userId) throws IOException {

        String userUrl = MANAGER_URL + USER_URL + "/" +userId ;
        String userContentUrl = MANAGER_URL + USERCONTENT_URL +"/" + userId ;

        String jsonData1 = apiService.doGet(userUrl);

        String jsonData2 = apiService.doGet(userContentUrl);
        System.out.println(jsonData2);

        if (null == jsonData1 || null == jsonData2 ) {
            return null;
        }
        User user = MAPPER.readValue(jsonData1,new TypeReference<User>() {} );
        UserContent userContent = MAPPER.readValue(jsonData2, new TypeReference<UserContent>() {});
        JsonNode jsonNode = MAPPER.readTree(jsonData2);
        String contact = jsonNode.get("contact").asText();
        String skill = jsonNode.get("skill").asText();
        String education = jsonNode.get("education").asText();
        String hobby = jsonNode.get("hobby").asText();
        String language = jsonNode.get("language").asText();
        String award = jsonNode.get("award").asText();
        String work_exp = jsonNode.get("workExp").asText();
        List<UserContactVo> userContactVos = MAPPER.readValue(contact, new TypeReference<List<UserContactVo>>() {});
        UserContentVo userContentVo = null;
        try {
            userContentVo = new UserContentVo();
            List<UserContactVo> userContactVo = MAPPER.readValue(contact, new TypeReference<List<UserContactVo>>() {});
            List<UserSkillVo> userSkillVo = MAPPER.readValue(skill, new TypeReference<List<UserSkillVo>>() {});
            List<UserEducationVo> userEducationVo = MAPPER.readValue(education, new TypeReference<List<UserEducationVo>>() {});
            List<UserLanguageVo> userLanguageVo = MAPPER.readValue(language, new TypeReference<List<UserLanguageVo>>() {});
            List<UserHobbyVo> userHobbyVo = MAPPER.readValue(hobby, new TypeReference<List<UserHobbyVo>>() {});
            List<UserAwardVo> userAwardVo = MAPPER.readValue(award, new TypeReference<List<UserAwardVo>>() {});
            List<UserWorkExpVo> userWorkExpVo = MAPPER.readValue(work_exp, new TypeReference<List<UserWorkExpVo>>() {});

            userContentVo.setId(userContent.getId());
            userContentVo.setAbout(userContent.getAbout());
            userContentVo.setMotto(userContent.getMotto());
            userContentVo.setPosition(userContent.getPosition());
            userContentVo.setUserId(userContent.getUserId());
            userContentVo.setContact(userContactVo);

            userContentVo.setEducation(userEducationVo);
            userContentVo.setAward(userAwardVo);
            userContentVo.setWork_exp(userWorkExpVo);
            userContentVo.setHobby(userHobbyVo);
            userContentVo.setLanguage(userLanguageVo);
            userContentVo.setSkill(userSkillVo);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new PersonInfoVo(user, userContentVo);
    }
}
