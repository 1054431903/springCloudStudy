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