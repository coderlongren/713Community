package com.community.manager.dao;

import com.community.manager.entity.MemberDownload;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : tingting
 * @Description :
 * @Date : 2017/11/17 20:50
 */
public interface MemberDownloadDao extends BaseDao<MemberDownload> {

    /**
     * 模糊查询会员下载信息
     *
     * @param nameandType
     * @return
     */
    List<MemberDownload> listMemberDownloadInfo(String nameandType);

    /**
     * 菜单搜索--分页查询
     *
     * @param searchParam
     * @param searchVal
     * @return
     */
    List<MemberDownload> listInfoWithSearch(@Param("param") String searchParam,
                                            @Param("searchVal") String searchVal);
}
