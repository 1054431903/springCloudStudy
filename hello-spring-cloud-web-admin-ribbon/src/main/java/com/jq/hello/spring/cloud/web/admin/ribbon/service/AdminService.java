package com.jq.hello.spring.cloud.web.admin.ribbon.service;

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

    public String sayHi() {
        return restTemplate.getForObject("http://hello-spring-cloud-service-admin/hi", String.class);
    }
}
