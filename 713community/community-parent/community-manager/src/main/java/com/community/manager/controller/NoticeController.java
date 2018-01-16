package com.community.manager.controller;

import com.community.manager.entity.Notice;
import com.community.manager.service.NoticeService;
import com.community.manager.vo.PageResultVo;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: huzisong
 * @Description:这是notice的controller
 * @Date: 2017/11/4 19:53.
 */
@RequestMapping("notice")
@Controller
public class NoticeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    /**
     * 分页查询社区信息
     *
     * @param page
     * @return
     */

    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewNoticeByPage(@PathVariable("next") Integer page) {

        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = noticeService.viewNoticeByPage(page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询公告信息失败！程序出错！--------------------------> methodName : NoticeController.viewNoticeByPage");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == pageResultVo || null == pageResultVo.getRows()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(pageResultVo);
    }

    /**
     * 新增公告
     *
     * @param notice
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> addNotice(Notice notice) {
        boolean result = false;
        boolean server_error_flag = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增公告 -->> notice = {}", notice.getId());
            }

            result = noticeService.addNotice(notice);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增公告成功 -->> notice = {}", notice.getId());
            }
        } catch (Exception e) {
            LOGGER.error("新增失败 -->> notice = {}", notice.getId());
            server_error_flag = true;
        }

        //程序报错
        if (server_error_flag) {
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        //更新失败
        if (!result) {
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        //新增成功,201
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * 更新公告
     *
     * @param id
     * @param notice
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> updateNoticeById(@PathVariable("id") Long id, Notice notice) {

        boolean result = false;
        boolean server_error_flag = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新公告 -->> notice = {}", notice);
            }

            result = noticeService.updateNotice(id,notice);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新公告成功 -->> notice = {}", notice.getId());
            }
        } catch (Exception e) {
            LOGGER.error("更新失败 -->> notice = {}", notice.getId());
            server_error_flag = true;
        }

        //程序报错
        if (server_error_flag) {
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        //更新失败
        if (!result) {
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        //修改成功,204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 根据id查询公告
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Notice> getNoticeById(@PathVariable("id") Long id){
        Notice notice = null;
        boolean server_error_flag = false;

        try {
            notice = noticeService.getNoticeById(id);
        } catch (Exception e) {
            LOGGER.error("查询出错 ----------------> methodName : NoticeControler.getNoticeById");
            server_error_flag = true;
        }

        //程序报错 - 500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //结果为空 - 404
        if (null == notice) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        //200
        return ResponseEntity.ok(notice);
    }


    /**
     * 删除公告
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeNoticeById(@PathVariable("id") Long id) {
        boolean server_error_flag = false;
        boolean result = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除菜单数据 ----------------> noticeId = {}",id);
            }

            result = noticeService.deleteNoticeById(id);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除公告成功 ----------------> menuId = {}",id);
            }
        } catch (Exception e) {
            LOGGER.error("删除失败！！程序出错-----------------> methodName : NoticeControler.removeNoticeById");
            server_error_flag = true;
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(null);

    }

    /**
     * 根据关键字进行搜索
     *
     * @param keyWord
     * @param page
     * @return
     */
    @GetMapping("/{keyWord}/page/{next}")
    public ResponseEntity<PageResultVo> searchNoticeByName( @PathVariable("keyWord") String keyWord, @PathVariable("next") Integer page){
        boolean server_error_flag = false;

        //如果请求参数为空
        if (StringUtils.isEmpty(keyWord)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = noticeService.getNoticeByName(keyWord,page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页搜索公告信息失败！程序出错！--------------> methodName : NoticeController.searchNoticeByName");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == pageResultVo || null == pageResultVo.getRows()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(pageResultVo);
    }

}
