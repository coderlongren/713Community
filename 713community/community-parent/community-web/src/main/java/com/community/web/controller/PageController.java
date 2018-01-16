package com.community.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: xian
 * @Description:该子系统的所有页面跳转控制器，即所有页面跳转都由此类控制
 * @Date:create in 2017/11/16 9:59
 */
@Controller
public class PageController {

    /**
     * 页面跳转
     *
     * @param page
     * @return
     */
    @GetMapping("{pageName}.html")
    public String toPage(@PathVariable("pageName") String page) {

        return page;
    }

    /**
     * 页面跳转
     *
     * @return
     */
    @GetMapping("{id}/{pageName}.html")
    public String toPageWithId(@PathVariable("pageName") String page) {

        return page;
    }
}
