package com.community.manager.controller;

import com.community.manager.entity.MemberDownload;
import com.community.manager.service.MemberDownloadService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : tingting
 * @Description : 会员下载相关的controller层
 * @Date : 2017/11/17 19:52
 */

@RequestMapping("memberDownload")
@Controller
public class MemberDownloadController {

    @Autowired
    private MemberDownloadService memberDownloadService;

    public static final Logger LOGGER = LoggerFactory.getLogger(MemberDownloadController.class);

    /**
     * 新增会员下载信息
     *
     * @param memberDownload
     * @return
     */
    @PostMapping("info")
    public ResponseEntity<Void> addMemberDownloadInfo(MemberDownload memberDownload) {

        boolean server_error_flag = false;

        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增会员下载信息, memberDownload = {}" , memberDownload);
            }

            result = memberDownloadService.insertMemberDownloadInfo(memberDownload);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增会员下载信息成功！ memberDownload = {}", memberDownload);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("新增会员下载信息失败！程序出错！------> methodName : MemberDownloadController.addMemberDownloadInfo" );
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if (!result) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 分页查询会员下载信息
     *
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("info/page/{next}")
    public ResponseEntity<PageResultVo> viewMemberDownloadInfoByPage(@PathVariable("next") Integer page,
                                                                     @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = memberDownloadService.listAllMemberDownloadInfoByPage(page, rows);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询会员下载信息失败！程序出错！--------> methodName : MemberDownloadController.viewMemberDownloadInfoByPage");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (pageResultVo == null || pageResultVo.getRows() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(pageResultVo);
    }

    /**
     * 更新会员下载信息
     *
     * @param infoId
     * @param memberDownload
     * @return
     */
    @PutMapping("info/{infoId}")
    public ResponseEntity<Void> updateMemberDownloadInfo(@PathVariable("infoId") Long infoId, MemberDownload memberDownload) {

        boolean server_error_flag = false;

        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新会员下载信息, typeId = {}, recruitInfo = {}", infoId, memberDownload);
            }

            result = memberDownloadService.updateMemberDownloadInfo(infoId, memberDownload);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新会员下载信息成功， typeId = {}， recruitType = {}", infoId, memberDownload);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("更新会员下载信息失败！程序出错！---------------->methodName：MemberDownloadController.updateMemberDownloadInfo");
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
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 模糊查询会员下载信息
     *
     * @param name
     * @param page
     * @return
     */
    @GetMapping("member/{name}/page/{next}")
    public ResponseEntity<PageResultVo> viewMemberDownloadInfo(@PathVariable("name") String name,
                                                               @RequestParam("rows") Integer rows,
                                                               @PathVariable("next") Integer page) {

        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = memberDownloadService.listMemberDownloadInfo(name, rows, page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询会员下载信息失败，程序出错！---------------> methodName : MemberDownloadController.viewMemberDownloadInfo");
        }

        if(pageResultVo == null || pageResultVo.getRows() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if(server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(pageResultVo);
    }

    /**
     * 根据id查询会员下载信息
     *
     * @param infoId
     * @return
     */
    @GetMapping("info/{infoId}")
    public ResponseEntity<MemberDownload> viewMemberDownloadById(@PathVariable("infoId") Long infoId) {

        boolean server_error_flag = false;

        MemberDownload memberDownload = null;

        try {
            memberDownload = memberDownloadService.getMemberDownloadById(infoId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询会员下载信息失败，程序出错！---------------> methodName : MemberDownloadController.viewMemberDownloadById");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (memberDownload == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(memberDownload);
    }

    /**
     * 根据id删除会员下载信息
     *
     * @param infoId
     * @return
     */
    @DeleteMapping("info/{infoId}")
    public ResponseEntity<Void> removeMemberDownloadById(@PathVariable("infoId") Long infoId) {

        boolean server_error_flag = false;

        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除会员下载信息，typeId = {}", infoId);
            }

            result = memberDownloadService.removeMemberDownloadById(infoId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除会员下载信息成功，typeId = {}", infoId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除会员下载信息失败！程序出错！---------> methodName: MemberDownloadController.removeMemberDownloadById");
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
     *资源信息搜索
     *
     * @param searchParam
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("param/page/{next}")
    public ResponseEntity<PageResultVo> searchInfo(@RequestParam("searchParam") String searchParam,
                                                   @RequestParam("searchVal") String searchVal,
                                                   @PathVariable("next") Integer page,
                                                   @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        boolean server_error_flag = false;

        if (StringUtil.isEmpty(searchVal)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        PageResultVo pageResult = null;

        try {
            pageResult = memberDownloadService.listInfoWithSearch(searchParam, searchVal, page, rows);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("资源信息搜索失败！程序出错！-------> methodName : MemberDownloadController.searchInfo");
        }

        if (pageResult == null || pageResult.getRows().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(pageResult);
    }

}
