package com.community.manager.dao;

import com.community.manager.entity.Notice;

import java.util.List;

/**
 * @Author: huzisong
 * @Description:
 * @Date: 2017/11/4 20:13.
 */
public interface NoticeDao extends BaseDao<Notice>{

    /**
     * 根据id查询公告
     *
     * @param id
     * @return
     */
    Notice getNoticeById(Long id);

    /**
     * 根据关键字模糊查询所有记录
     *
     * @param keyWord
     * @return
     */
    List<Notice> listAllByName(String keyWord);
}
