package com.community.manager.service.impl;

import com.community.manager.dao.NoticeDao;
import com.community.manager.entity.Notice;
import com.community.manager.service.NoticeService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: huzisong
 * @Description:
 * @Date: 2017/11/4 20:06.
 */
@Service
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    private NoticeDao noticeDao;

    /**
     * 根据id进行分页查询
     *
     * @param page
     * @return
     */
    @Override
    public PageResultVo viewNoticeByPage(Integer page) {
        //为了提升系统的健壮性，先判断page是否为空，为空则设一个默认值1
        if (page == null || page <= 0) {
            page = 1;
        }
        int rows = 10;

        //已下两条语句在一起处理分页查询
        PageHelper.startPage(page, rows);
        List<Notice> list = noticeDao.listAll();


        PageInfo<Notice> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 添加公告信息
     *
     * @param notice
     * @return
     */
    @Override
    public Boolean addNotice(Notice notice) {
        notice.setCreateTime(new Date());
        notice.setUpdateTime(new Date());
        notice.setReleaseTime(new Date());
        return noticeDao.insert(notice) == 1;
    }

    /**
     * 更新公告
     *
     * @param id
     * @param notice
     * @return
     */
    @Override
    public Boolean updateNotice(Long id, Notice notice) {
        notice.setUpdateTime(new Date());
        notice.setId(id);
        return noticeDao.update(notice) == 1;
    }

    /**
     * 根据id查询公告
     *
     * @param id
     * @return
     */
    @Override
    public Notice getNoticeById(Long id) {
        return noticeDao.getById(id);
    }

    /**
     * 根据id删除公告
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteNoticeById(Long id) {

        return noticeDao.deleteById(id) == 1;
    }

    /**
     * 根据关键字进行搜索
     *
     * @param keyWord
     * @param page
     * @return
     */
    @Override
    public PageResultVo getNoticeByName(String keyWord, Integer page) {
        //为了提升系统的健壮性，先判断page是否为空，为空则设一个默认值1
        if (page == null || page <= 0) {
            page = 1;
        }

        //默认显示10条数据
        int rows = 10;

        //已下两条语句在一起处理分页查询
        PageHelper.startPage(page, rows);
        String value = new StringBuilder("%").append(keyWord).append("%").toString();
        List<Notice> list = noticeDao.listAllByName(value);


        PageInfo<Notice> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

}
