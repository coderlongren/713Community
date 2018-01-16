package com.community.manager.service.impl;

import com.community.manager.dao.RecruitInfoDao;
import com.community.manager.dao.RecruitTypeDao;
import com.community.manager.entity.RecruitInfo;
import com.community.manager.entity.RecruitType;
import com.community.manager.service.RecruitInfoService;
import com.community.manager.vo.PageResultVo;
import com.community.manager.vo.RecruitAndTypeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/6 21:23
 */
@Service
public class RecruitInfoServiceImpl implements RecruitInfoService {

    @Autowired
    private RecruitInfoDao recruitInfoDao;
    @Autowired
    private RecruitTypeDao recruitTypeDao;

    /**
     * 新增招新标准详情信息
     *
     * @param recruitInfo
     * @return
     */
    @Override
    public Boolean insertRecruitInfo(RecruitInfo recruitInfo) {
        recruitInfo.setCreateTime(new Date());
        recruitInfo.setUpdateTime(new Date());

        return recruitInfoDao.insert(recruitInfo) == 1;
    }

    /**
     * 更新招新标准详情信息
     *
     * @param typeId
     * @param recruitInfo
     * @return
     */
    @Override
    public Boolean updateRecruitInfo(Long typeId, RecruitInfo recruitInfo) {
        recruitInfo.setId(typeId);
        recruitInfo.setUpdateTime(new Date());

        return recruitInfoDao.update(recruitInfo) == 1;
    }

    /**
     *根据id删除招新标准详情信息
     *
     * @param typeId
     * @return
     */
    @Override
    public Boolean deleteRecruitInfoById(Long typeId) {
        return recruitInfoDao.deleteById(typeId) == 1;
    }

    /**
     * 分页查询招新标准详情信息
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listAllRecruitInfoByPage(Integer page, Integer rows) {
        if (page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page, rows);

        List<RecruitInfo>list = recruitInfoDao.listAll();
        PageInfo<RecruitInfo>pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 根据id查询招新标准详情信息
     *
     * @param infoId
     * @return
     */
    @Override
    public RecruitAndTypeVo getRecruitInfoById(Long infoId) {
        RecruitInfo recruitInfo = recruitInfoDao.getById(infoId);
        List<RecruitType> recruitTypes = recruitTypeDao.listAll();
        return new RecruitAndTypeVo(recruitInfo, recruitTypes);
    }



}
