package com.community.manager.controller;

import com.community.manager.entity.Community;
import com.community.manager.service.CommunityService;
import com.community.manager.vo.PageResultVo;
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
 * @Description: community相关的controller
 * @Date: 2017/11/2 19:29.
 */
@RequestMapping("community")
@Controller
public class CommunityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommunityController.class);

    @Autowired
    private CommunityService communityService;

    /**
     * 分页查询社区信息
     *
     * @param page 下一页
     * @return 分页数据传输对象
     */
    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewCommunityByPage(@PathVariable("next") Integer page) {

        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
           pageResultVo = communityService.viewCommunityByPage(page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询社区信息失败失败！程序出错！-----> methodName : CommunityController.viewCommunityByPage");
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
     * 添加社区信息
     *
     * @param community
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> addCommunity(Community community){
        boolean result = false;
        boolean server_error_flag = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增社区信息 -->> community = {}", community);
            }

            result = communityService.addCommunity(community);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增社区信息成功 ---> community = {}", community);
            }
        } catch (Exception e) {
            LOGGER.error("新增社区信息失败 ----> community = {}", community);
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
     * 根据id删除社区信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeCommunityById(@PathVariable("id") Long id){
        boolean server_error_flag = false;
        boolean result = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除社区信息 ----------------> id = {}",id);
            }

            result = communityService.deleteCommunityById(id);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除社区信息成功 -------> Id = {}",id);
            }
        } catch (Exception e) {
            LOGGER.error("删除失败！！程序出错--------> methodName : CommunityController.removeCommunityById");
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
     * 更新社区信息
     *
     * @param id
     * @param community
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<Boolean> updateCommunity(@PathVariable("id") Long id, Community community){
        boolean result = false;
        boolean server_error_flag = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新社区信息 -->> id = {}", community);
            }

            result = communityService.updateCommunity(id,community);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新菜单成功 -->> id = {}", community.getId());
            }
        } catch (Exception e) {
            LOGGER.error("更新失败 -->> id = {}", community.getId());
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
     * 根据id查询社区信息
     *
     * @param id
     * @return
     */

    @GetMapping("{id}")
    public ResponseEntity<Community> viewCommunityById(@PathVariable("id")  Long id) {
        Community community = null;
        boolean server_error_flag = false;

        try {
            community = communityService.getCommunityById(id);
        } catch (Exception e) {
            LOGGER.error("查询出错 -----> methodName : CommunityControler.viewCommunityById");
            server_error_flag = true;
        }

        //程序报错 - 500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //结果为空 - 404
        if (null == community) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        //200
        return ResponseEntity.ok(community);
    }

    /**
     * 根据关键字进行搜索
     *
     * @param keyWord
     * @param page
     * @return
     */
    @GetMapping("/{keyWord}/page/{next}")
    public ResponseEntity<PageResultVo> searchCommunityByName( @PathVariable("keyWord") String keyWord, @PathVariable("next") Integer page){
        boolean server_error_flag = false;

        //如果请求参数为空
        if (StringUtils.isEmpty(keyWord)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = communityService.getCommunityByName(keyWord,page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页搜索公告信息失败！程序出错！--------------> methodName : CommunityController.searchCommunityByName");
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
