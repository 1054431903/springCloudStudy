package com.jq.hello.spring.cloud.service.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jq
 * @Date: 2019/2/11 15:49
 */
@RestController
public class AdminController {

    @Value("${server.port}")
    private String port;

//    @RequestMapping(value = "hi",method = RequestMethod.GET)
//    public String sayHi() {
//        return String.format("hello spring cloud and port is : %s", port);
//    }
    @RequestMapping(value = "hi",method = RequestMethod.GET)
    public String sayHi(String msg) {
        return String.format("hello spring cloud and port is : %s \n message : %s", port, msg);
    }
}
