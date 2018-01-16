package com.community.manager.service.impl;

import com.community.manager.dao.AchievementDao;
import com.community.manager.entity.Achievement;
import com.community.manager.service.AchievementService;
import com.community.manager.vo.AchievementMemberVo;
import com.community.manager.vo.MembersVo;
import com.community.manager.vo.PageResultVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author:王喜
 * @Description :AchievementService接口实现类
 * @Date: 2017/11/14 0014 17:27
 */
@Service
public class AchievementServiceImpl implements AchievementService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private AchievementDao achievementDao ;

    /**
     * 分页展示出所有的项目成果信息
     *
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAllAchievementByPage(Integer page) {
        //测试数据
//        [{"memberName":"a","memberUrl":"www.baidu.com"},{"memberName":"wangxi","memberUrl":"qq.com"}]


        if (page <= 0 || null == page) {
            page = 1;
        }
        int rows = 10;
        /*
         * page:表示下一页
         * rows:表示每一页显示的记录数
         */
        PageHelper.startPage(page, rows);
        List<Achievement> achievements = achievementDao.listAll();

        //得到数据库记录的总数
        PageInfo<Achievement> pageInfo1 = new PageInfo<>(achievements);

        List<AchievementMemberVo> achievementMemberVos = new ArrayList<>();
        String jsonData = null;
        //把数据库数据转换成json格式
        List<MembersVo> membersList = new ArrayList<>();
        for (Achievement achievement : achievements) {
            //把每一行的信息中的members取出来，并转换成list集合形式
            AchievementMemberVo achievementMemberVo = new AchievementMemberVo();
            achievementMemberVo.setAchievement(achievement);

            jsonData = achievement.getMembers();
            try {
                //利用MAPPER把json格式数据转换成lsit
                membersList = (List<MembersVo>) MAPPER.readValue(jsonData,new TypeReference<List<MembersVo>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }

            achievementMemberVo.setMembersVoList(membersList);

            achievementMemberVos.add(achievementMemberVo);
        }
        //便于垃圾收集
//        achievementMemberVo = null;
        jsonData = null;
        membersList = null;

        //pageInfo是经过上面处理过的
        PageInfo<AchievementMemberVo> pageInfo = new PageInfo<>(achievementMemberVos);

        return new PageResultVo(pageInfo1.getTotal(),pageInfo.getList(),pageInfo1.getPages());
    }

    /**
     * 根据id修改项目成果的状态
     *
     * @param achievementId
     * @param showFlag
     * @return
     */
    @Override
    public Boolean updateAchievementById(Long achievementId, Integer showFlag) {
        return achievementDao.updateAchievementById(achievementId,showFlag) == 1;
    }

    /**
     * 根据id删除项目成果
     *
     * @param achievementId
     * @return
     */
    @Override
    public Boolean removeAchievementById(Long achievementId) {
        return achievementDao.deleteById(achievementId) == 1;
    }

    /**
     * 增加项目成果信息
     *
     * @param achievement
     * @return
     */
    @Override
    public Boolean insertAchievement(Achievement achievement) {
        String jsonData = null;
        List<MembersVo> membersVos = new ArrayList<>();
        //分割
        String members = achievement.getMembers();
        String[] result = members.split(",");
        for (int i = 0; i < result.length; i = i + 1 ) {
            membersVos.add(new MembersVo(result[i],result[i+1]));
            i = i + 1;
        }
        try {
            //把list集合转换为json格式
            jsonData = MAPPER.writeValueAsString(membersVos);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        achievement.setMembers(jsonData);
        achievement.setCreateTime(new Date());
        achievement.setUpdateTime(new Date());
        achievement.setShowFlag(1);
        return achievementDao.insert(achievement) == 1;
    }

    /**
     * 模糊搜索项目成果信息
     *
     * @param searchKeywords
     * @param searchParam
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAchievementWithSearch(String searchKeywords, String searchParam, Integer page) {
        if (page <= 0 || null == page) {
            page = 1;
        }
        int rows = 10;
        /*
         * page:表示下一页
         * rows:表示每一页显示的记录数
         */
        //拼接模糊查询的字符串，，拼接%，不在mapper中进行拼接,对关键字进行拼接
        searchKeywords = new StringBuilder("%").append(searchKeywords).append("%").toString();

        PageHelper.startPage(page, rows);
        List<Achievement> achievements = achievementDao.listAchievementWithSearch(searchParam,searchKeywords);

        //得到数据库记录的总数
        PageInfo<Achievement> pageInfo1 = new PageInfo<>(achievements);

        List<AchievementMemberVo> achievementMemberVos = new ArrayList<>();
        String jsonData = null;
        //把数据库数据转换成json格式
        List<MembersVo> membersList = new ArrayList<>();
        for (Achievement achievement : achievements) {
            //把每一行的信息中的members取出来，并转换成list集合形式
            AchievementMemberVo achievementMemberVo = new AchievementMemberVo();
            achievementMemberVo.setAchievement(achievement);

            jsonData = achievement.getMembers();
            try {
                //利用MAPPER把json格式数据转换成lsit
                membersList = (List<MembersVo>) MAPPER.readValue(jsonData,new TypeReference<List<MembersVo>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }

            achievementMemberVo.setMembersVoList(membersList);

            achievementMemberVos.add(achievementMemberVo);
        }
        //便于垃圾收集
        jsonData = null;
        membersList = null;

        PageInfo<AchievementMemberVo> pageInfo = new PageInfo<>(achievementMemberVos);

        return new PageResultVo(pageInfo1.getTotal(),pageInfo.getList(),pageInfo1.getPages());
    }

    /**
     * 根据id查询项目成果介绍
     *
     * @param achievement_id
     * @return
     */
    @Override
    public Achievement getAchievementIntroductionById(Long achievement_id) {
        return achievementDao.getById(achievement_id);
    }

    /**
     * 根据id查询项目成果信息
     *
     * @param achievementId
     * @return
     */
    @Override
    public AchievementMemberVo getAchievementById(Long achievementId) {
        //先查出Achievement
        Achievement achievement = achievementDao.getById(achievementId);
        AchievementMemberVo achievementMemberVo = new AchievementMemberVo();
        achievementMemberVo.setAchievement(achievement);

        String jsonData = null;

        //得到项目成员信息
        jsonData = achievement.getMembers();
        List<MembersVo> membersList = new ArrayList<>();
        try {
            //利用MAPPER把json格式数据转换成lsit
            membersList = (List<MembersVo>) MAPPER.readValue(jsonData,new TypeReference<List<MembersVo>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        achievementMemberVo.setMembersVoList(membersList);

        achievementMemberVo.setMembersVoListSize(membersList.size());

        return achievementMemberVo;
    }

    /**
     * 根据id更新项目信息
     *
     * @param achievementId
     * @param achievement
     * @return
     */
    @Override
    public Boolean updateAchievementByAchievementId(Long achievementId, Achievement achievement) {
        achievement.setId(achievementId);
        achievement.setUpdateTime(new Date());

        //把前台接收的成员数据变为json格式数据
        String jsonData = null;
        List<MembersVo> membersVos = new ArrayList<>();
        //分割
        String members = achievement.getMembers();
        String[] result = members.split(",");
        for (int i = 0; i < result.length; i = i + 1 ) {
            membersVos.add(new MembersVo(result[i],result[i+1]));
            i = i + 1;
        }
        try {
            //变为json格式
            jsonData = MAPPER.writeValueAsString(membersVos);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        achievement.setMembers(jsonData);

        return achievementDao.update(achievement) == 1;
    }

    /**
     * 查询所有的项目成果信息
     *
     * @return
     */
    @Override
    public List<AchievementMemberVo> listAllAchievement() {

        List<Achievement> achievements = achievementDao.listAll();

        List<AchievementMemberVo> achievementMemberVos = new ArrayList<>();

        List<MembersVo> membersVos = new ArrayList<>();

        //遍历每个项目
        for (Achievement achievement : achievements) {
            AchievementMemberVo achievementMemberVo = new AchievementMemberVo();
            achievementMemberVo.setAchievement(achievement);
            //把json格式的数据转换成List集合
            String jsonData = achievement.getMembers();

            try {
                membersVos = (List<MembersVo>) MAPPER.readValue(jsonData,new TypeReference<List<MembersVo>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            achievementMemberVo.setMembersVoList(membersVos);

            achievementMemberVos.add(achievementMemberVo);
        }

        //垃圾回收
        membersVos = null;
        return achievementMemberVos;
    }
}
