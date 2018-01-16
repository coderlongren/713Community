package com.community.manager.service.impl;

import com.community.manager.dao.RecruitTypeDao;
import com.community.manager.entity.RecruitType;
import com.community.manager.service.RecruitTypeService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/3 19:02
 */
@Service
public class RecruitTypeServiceImpl implements RecruitTypeService{

    @Autowired
    private RecruitTypeDao recruitTypeDao;

    /**
     * 新增招新信息
     *
     * @param recruitType
     * @return
     */
    @Override
    public Boolean insertRecruit(RecruitType recruitType) {
        recruitType.setCreateTime(new Date());
        recruitType.setUpdateTime(new Date());

        return recruitTypeDao.insert(recruitType) == 1;
    }

    /**
     * 分页查询招新信息
     *
     * @param page
     * @param rows
     * @return
     */
    public PageResultVo listAllRecruitByPage(Integer page, Integer rows) {
        if (page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page, rows);

        List<RecruitType>list = recruitTypeDao.listAll();
        PageInfo<RecruitType>pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 更新招新信息
     *
     * @param typeId
     * @param recruitType
     * @return
     */
    public Boolean updateRecruit(Long typeId, RecruitType recruitType) {
        recruitType.setUpdateTime(new Date());
        recruitType.setId(typeId);
        return recruitTypeDao.update(recruitType) == 1;
    }

    /**
     * 根据Id查询招新信息
     *
     * @param typeId
     * @return
     */
    public RecruitType getRecruitTypeById(Long typeId) {
        return recruitTypeDao.getById(typeId);
    }

    /**
     * 根据Id删除招新信息
     *
     * @param typeId
     * @return
     */
    public Boolean removeRecruitTypeById(Long typeId) {
        return recruitTypeDao.deleteById(typeId) == 1;
    }

    /**
     * 根据招新信息模糊搜索类型
     *
     * @param recruitType
     * @param rows
     * @param page
     * @return
     */
    public PageResultVo listTypeByInformation(String recruitType, Integer rows, Integer page) {

        String recruitInformation = new StringBuilder("%").append(recruitType).append("%").toString();

        if (rows == null) {
            rows = 10;
        }

        if (page <= 0) {
            page = 1;
        }

        PageHelper.startPage(page, rows);
        List<RecruitType> list = recruitTypeDao.listTypeByInformation(recruitInformation);

        PageInfo<RecruitType> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(),pageInfo.getList(),pageInfo.getPages());
    }

    /**
     * 查询全部的招新类型
     *
     * @return
     */
    @Override
    public List<RecruitType> listAllRecruitType() {
        return recruitTypeDao.listAll();
    }
}
