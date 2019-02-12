# Spring Cloud Netflix 学习

### 准备阶段

创建一个projects文件夹以供之后的项目添加


### 创建统一的依赖管理

Spring Cloud 项目都是基于 Spring Boot 进行开发，并且都是使用 Maven 做项目管理工具。在实际开发中，我们一般都会创建一个依赖管理项目作为 Maven 的 Parent 项目使用，这样做可以极大的方便我们对 Jar 包版本的统一管理。

在这一环节建了一个hello-spring-cloud-dependencies的文件夹来做其他子项目的依赖管理项目

### 创建服务注册中心
eureka服务注册中心的创建(hello-spring-cloud-eureka)，具体pom，以及配置的编写
### 创建服务提供者
...-service-admin

其中要在主类上加上@EnableEurekaClient声明自己是一个eureka客户端，注册服务的

然后yml文件的配置中，spring.application.name很重要，以后的服务间相互调用就是用的这个名字

打开服务注册中心，开启服务，刷新服务注册中心，可以看到已有服务注册进来了

### 创建服务消费者（Ribbon）
Ribbon 是一个负载均衡客户端，可以很好的控制 `http` 和 `tcp` 的一些行为。

configuration 里配置注入 RestTemplate 的bean，通过@LoadBalanced开启负载均衡功能（轮询，具体没有做研究）
### 创建服务消费者（Feign）
Feign 是一个声明式的伪 Http 客户端，它使得写 Http 客户端变得更简单。使用 Feign，只需要创建一个接口并注解。它具有可插拔的注解特性，可使用 Feign 注解和 JAX-RS 注解。Feign 支持可插拔的编码器和解码器。Feign 默认集成了 Ribbon，并和 Eureka 结合，默认实现了负载均衡的效果
- Feign 采用的是基于接口的注解
- Feign 整合了 ribbon

开发中使用feign

具体的使用参考service中的AdminService
### 使用熔断器防止服务雪崩
我们在分布式中保证高可用的n个9(99.999...%)服务的过程中，不可避免的就是服务出现问题，
如果单个服务出现问题，调用这个服务就会出现线程阻塞，此时若有大量的请求涌入，Servlet 容器的线程资源会被消耗完毕，导致服务瘫痪。
服务与服务之间的依赖性，故障会传播，会对整个微服务系统造成灾难性的严重后果，这就是服务故障的 “雪崩” 效应。

###### 为了解决这个问题，业界提出了熔断器模型。
Netflix 开源了 Hystrix 组件，实现了熔断器模式，Spring Cloud 对这一组件进行了整合。

较底层的服务如果出现故障，会导致连锁故障。当对特定的服务的调用的不可用达到一个阀值（Hystrix 是 5 秒 20 次） 熔断器将会被打开。

熔断器打开后，为了避免连锁故障，通过 `fallback` 方法可以直接返回一个固定值。
###### Ribbon 中使用熔断器
在` pom.xml` 中增加依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```
在 Application 中增加 `@EnableHystrix` 注解   -- 参考`WebAdminRibbonApplication`--

在 Service 中增加 `@HystrixCommand` 注解
-- 在 Ribbon 调用方法上增加 `@HystrixCommand` 注解并指定 `fallbackMethod` 熔断方法 --
参考ribbon的service

###### Feign 中使用熔断器
Feign 是自带熔断器的，但默认是关闭的。需要在配置文件中配置打开它，在配置文件增加以下代码：
```yml
feign:
  hystrix:
    enabled: true
```
在 Service 中增加 fallback 指定类 (参考AdminService)

创建熔断器类(AdminServiceHystrix)并实现对应的 Feign 接口
### 使用熔断器仪表盘监控
在 Ribbon 和 Feign 项目增加 Hystrix 仪表盘功能，两个项目的改造方式相同

在 `pom.xml` 中增加依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>
```
在 Application 中增加 `@EnableHystrixDashboard` 注解

创建 `hystrix.stream` 的 Servlet 配置

测试 Hystrix Dashboard(
浏览器端访问 http://localhost:xxxx/hystrix)