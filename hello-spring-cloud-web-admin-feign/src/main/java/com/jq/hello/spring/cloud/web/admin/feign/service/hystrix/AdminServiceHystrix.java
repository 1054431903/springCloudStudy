package com.jq.hello.spring.cloud.web.admin.feign.service.hystrix;

import com.jq.hello.spring.cloud.web.admin.feign.service.AdminService;
import org.springframework.stereotype.Component;

/**
 * @Author: jq
 * @Date: 2019/2/11 19:16
 */
@Component
public class AdminServiceHystrix implements AdminService {
    @Override
    public String sayHi(String message) {
        return String.format("[sayHi error] msg = %s",message);
    }
}
