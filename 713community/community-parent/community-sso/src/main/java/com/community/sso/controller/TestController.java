package com.community.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: xian
 * @Description:
 * @Date:create in 2017/10/14 22:52
 */
@Controller
public class TestController {

    @GetMapping("{index}")
    public String toIndex(@PathVariable("index") String index) {
        System.out.println("hahahhha");
        return index;
    }
}
