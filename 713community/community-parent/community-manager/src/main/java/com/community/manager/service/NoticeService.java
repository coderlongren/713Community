package com.community.manager.service;

import com.community.manager.entity.Notice;
import com.community.manager.vo.PageResultVo;
import org.springframework.stereotype.Service;

/**
 * @Author: huzisong
 * @Description:
 * @Date: 2017/11/4 20:03.
 */
public interface NoticeService {
    /**
     * 分页查询notice
     *
     * @param page
     * @return
     */
    PageResultVo viewNoticeByPage (Integer page);

    /**
     * 新增公告
     *
     * @param notice
     * @return
     */
    Boolean addNotice (Notice notice);

    /**
     * 更新公告
     *
     * @param id
     * @param notice
     * @return
     */

    /**
     * 更新公告
     *
     * @param id
     * @param notice
     * @return
     */
    Boolean updateNotice (Long id, Notice notice);

    /**
     * 根据id查询公告
     *
     * @param id
     * @return
     */
    Notice getNoticeById(Long id);

    /**
     * 根据id删除公告
     *
     * @param id
     * @return
     */
    boolean deleteNoticeById(Long id);

    /**
     * 根据关键字进行分页搜索
     *
     * @param keyWord 关键字
     * @param page
     * @return
     */
    PageResultVo getNoticeByName(String keyWord, Integer page);
}
