package com.community.manager.controller;

import com.community.manager.entity.HomePage;
import com.community.manager.entity.HomePage;
import com.community.manager.service.HomePageService;
import com.community.manager.service.HomePageService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.StringUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.omg.PortableInterceptor.HOLDING;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @Author : tingting
 * @Description : 网站首页导航的controller层
 * @Date : 2017/11/6 21:19
 */

@RequestMapping("homepage")
@Controller
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    public static final Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);

    /**
     * 新增首页导航信息
     *
     * @param homePage
     * @return
     */
    @PostMapping("info")
    public ResponseEntity<Void> addHomePageInfo(HomePage homePage) {

        boolean server_error_flag = false;

        boolean result = false;

        try {
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info("新增首页导航信息-----> homepage = {}", homePage);
            }

            result = homePageService.insertHomePageInfo(homePage);

            if(LOGGER.isInfoEnabled()) {
                LOGGER.info("新增首页导航信息成功------> homepage = {}",homePage);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("新增首页导航信息失败！程序出错！------> methodName ： HomePageController.addhomePageInfo");
        }

        if(server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if(!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 分页查询首页导航信息
     *
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("info/page/{next}")
    public ResponseEntity<PageResultVo> viewHomePageInfoByPage(@PathVariable("next") Integer page,
                                                               @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        boolean server_error_flag = false;
        PageResultVo pageResultVo = null;

        try {
            pageResultVo = homePageService.listAllHomePageInfoByPage(page, rows);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询首页导航信息分页查询失败，程序出错！------> methodName : HomePageController.viewHomePageInfoByPage");
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
     * 更新首页导航信息
     *
     * @param typeId
     * @param homePage
     * @return
     */
    @PutMapping("info/{typeId}")
    public ResponseEntity<Void> updateHomePageInfo(@PathVariable("typeId") Long typeId, HomePage homePage) {
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新首页导航信息, typeId = {}, homePage = {}", typeId, homePage);
            }

            result = homePageService.updateHomePageInfo(typeId, homePage);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新首页导航信息成功, typeId = {}, homepage = {}", typeId, homePage);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("更新首页导航信息失败！程序出错！------>methodNname : HomePageController.updateHomaPageInfo");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 根据id删除首页导航信息
     *
     * @param infoId
     * @return
     */
    @DeleteMapping("info/{infoId}")
    public ResponseEntity<Void> removeHomePageInfo(@PathVariable("infoId") Long infoId) {
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("根据id删除首页导航信息, typeId = {}", infoId);
            }

            result = homePageService.deleteHomePageInfoById(infoId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("根据id删除首页导航信息成功, typeId = {}", infoId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除首页导航信息失败！程序出错！---------> methodName: HomaPageController.removeHomaPageInfo");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (!result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        //204
        return ResponseEntity.ok(null);
    }

    /**
     * 根据id查询首页导航信息
     *
     * @param infoId
     * @return
     */
    @GetMapping("info/{infoId}")
    public ResponseEntity<HomePage> viewHomePageInfoById(@PathVariable("infoId") Long infoId) {

        boolean server_error_flag = false;

        HomePage homePage = null;

        try {
            homePage = homePageService.getHomePageInfoById(infoId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询首页导航信息失败，程序出错！----------> methodName : HomePageController.viewHomePageInfoById");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (homePage == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(homePage);
    }

    /**
     * 根据首页栏目导航名模糊查询名称
     *
     * @param searchVal
     * @param rows
     * @param page
     * @return
     */
    @GetMapping("{searchVal}/page/{next}")
    public ResponseEntity<PageResultVo> viewHomePageWithSearch(@PathVariable("searchVal") String searchVal,
                                                               @PathVariable("next") Integer page,
                                                               @RequestParam("rows") Integer rows) {
        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = homePageService.listHomePageWithSearch(searchVal, page, rows);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("根据首页栏目导航名模糊查询名称失败！程序出错！------> methodName : HomePageController.viewHomePageWithSearch");
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (pageResultVo == null || pageResultVo.getRows() == null) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(pageResultVo);
    }
}
