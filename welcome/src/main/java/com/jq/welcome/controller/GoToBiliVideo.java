package com.jq.welcome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: jq
 * @Date: 2019/2/15 18:47
 */
@Controller
public class GoToBiliVideo {
    @RequestMapping(value = {"","index"})
    public String go() {
        return "redirect:https://www.bilibili.com/video/av35958304";
    }
}
