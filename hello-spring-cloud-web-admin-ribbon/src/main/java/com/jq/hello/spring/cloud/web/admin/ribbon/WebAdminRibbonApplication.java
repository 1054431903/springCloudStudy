package com.jq.hello.spring.cloud.web.admin.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author: jq
 * @Date: 2019/2/11 16:17
 */
//@SpringBootApplication //springboot
//@EnableDiscoveryClient // 发现服务提供者
//@EnableHystrix         //断路器
@SpringCloudApplication  //@SpringCloudApplication中整合了SpringBoot,发现eureka客户端,断路器hystrix
@EnableHystrixDashboard
public class WebAdminRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminRibbonApplication.class, args);
    }
}
