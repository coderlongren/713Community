package com.community.personal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: xian
 * @Description: 页面跳转控制器
 * @Date:create in 2017/12/16 14:50
 */
@Controller
public class PageController {

    @GetMapping("{pageName}.html")
    public String toPage(@PathVariable("pageName") String page) {

        return page;
    }

    @GetMapping("{id}/{pageName}.html")
    public String toPageWithId(@PathVariable("pageName") String page) {

        return page;
    }
}
