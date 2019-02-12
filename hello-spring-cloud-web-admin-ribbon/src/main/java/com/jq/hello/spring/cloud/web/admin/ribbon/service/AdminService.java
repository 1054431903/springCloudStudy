package com.jq.hello.spring.cloud.web.admin.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: jq
 * @Date: 2019/2/11 16:36
 */
@Service
public class AdminService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String sayHi(String msg) {
        return restTemplate.getForObject("http://hello-spring-cloud-service-admin/hi?msg="+msg, String.class);
    }

    public String hiError(String msg) {
        return String.format("error! msg : %s", msg);
    }
}
