package com.community.manager.service;

import com.community.manager.dao.BaseDao;
import com.community.manager.entity.MemberDownload;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageInfo;
import org.omg.PortableInterceptor.INACTIVE;

import java.security.acl.LastOwnerException;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/17 19:59
 */
public interface MemberDownloadService {

    /**
     * 新增会员下载信息
     *
     * @param memberDownload
     * @return
     */
    Boolean insertMemberDownloadInfo(MemberDownload memberDownload);

    /**
     * 分页查询会员下载信息
     *
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listAllMemberDownloadInfoByPage(Integer page, Integer rows);

    /**
     * 更新会员下载信息
     *
     * @param infoId
     * @param memberDownload
     * @return
     */
    Boolean updateMemberDownloadInfo(Long infoId, MemberDownload memberDownload);

    /**
     * 模糊查询会员下载信息
     *
     * @param name
     * @param rows
     * @param page
     * @return
     */
    PageResultVo listMemberDownloadInfo(String name, Integer rows, Integer page);

    /**
     * 根据id查询会员下载信息
     *
     * @param infoId
     * @return
     */
    MemberDownload getMemberDownloadById(Long infoId);

    /**
     * 根据id删除会员下载信息
     *
     * @param infoId
     * @return
     */
    Boolean removeMemberDownloadById(Long infoId);

    /**
     * 资源信息搜索-分页查询
     *
     * @param searchParam
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listInfoWithSearch(String searchParam, String searchVal, Integer page, Integer rows);
}
