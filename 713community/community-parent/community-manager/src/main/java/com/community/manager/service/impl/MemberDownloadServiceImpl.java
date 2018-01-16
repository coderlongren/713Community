package com.community.manager.service.impl;

import com.community.manager.dao.MemberDownloadDao;
import com.community.manager.entity.MemberDownload;
import com.community.manager.service.MemberDownloadService;
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
 * @Date : 2017/11/17 19:59
 */
@Service
public class MemberDownloadServiceImpl implements MemberDownloadService{

    @Autowired
    private MemberDownloadDao memberDownloadDao;

    /**
     * 新增会员下载信息
     *
     * @param memberDownload
     * @return
     */
    @Override
    public Boolean insertMemberDownloadInfo(MemberDownload memberDownload) {
        memberDownload.setCreateTime(new Date());
        memberDownload.setUpdateTime(new Date());

        return memberDownloadDao.insert(memberDownload) == 1;
    }

    /**
     * 分页查询会员下载信息
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listAllMemberDownloadInfoByPage(Integer page, Integer rows) {
        if (page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page, rows);

        List<MemberDownload> list = memberDownloadDao.listAll();
        PageInfo<MemberDownload> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 更新会员下载信息
     *
     * @param infoId
     * @param memberDownload
     * @return
     */
    @Override
    public Boolean updateMemberDownloadInfo(Long infoId, MemberDownload memberDownload) {
        memberDownload.setUpdateTime(new Date());
        memberDownload.setId(infoId);

        return memberDownloadDao.update(memberDownload) == 1;

    }

    /**
     * 模糊查询会员下载信息
     *
     * @param name
     * @param rows
     * @param page
     * @return
     */
    @Override
    public PageResultVo listMemberDownloadInfo(String name, Integer rows, Integer page) {
        String nameandType = new StringBuilder("%").append(name).append("%").toString();

        if (rows == null) {
            rows = 10;
        }

        if (page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page, rows);
        List<MemberDownload> list = memberDownloadDao.listMemberDownloadInfo(nameandType);

        PageInfo<MemberDownload> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 根据id查询会员下载信息
     *
     * @param infoId
     * @return
     */
    @Override
    public MemberDownload getMemberDownloadById(Long infoId) {
        return memberDownloadDao.getById(infoId);
    }

    /**
     * 根据id删除会员下载信息
     *
     * @param infoId
     * @return
     */
    @Override
    public Boolean removeMemberDownloadById(Long infoId) {
        return memberDownloadDao.deleteById(infoId) == 1;
    }

    /**
     * 资源信息搜索-分页查询
     *
     * @param searchParam
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listInfoWithSearch(String searchParam, String searchVal, Integer page, Integer rows) {
        if (page == null || page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page, rows);
        String value = new StringBuilder("%").append(searchVal).append("%").toString();
        List<MemberDownload> list = memberDownloadDao.listInfoWithSearch(searchParam, value);
        PageInfo<MemberDownload> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }
}
