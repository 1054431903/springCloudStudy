package com.jq.hello.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: jq
 * @Date: 2019/2/13 15:09
 */
@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 配置过滤类型，有四种不同生命周期的过滤器类型
     * 1. pre：路由之前
     * 2. routing：路由之时
     * 3. post：路由之后
     * 4. error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     * 通过int值来定义过滤器的执行顺序，数值越小优先级越高。
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //请求上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //设置字符集，解决返回页面的中文乱码
        currentContext.getResponse().setContentType("text/html;charset=utf-8");

        String token = request.getParameter("token");

        if (token == null) {
            //发送zuul的响应为false，不发送了就
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);

            try {
                currentContext.getResponse().getWriter().write("非法请求");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
