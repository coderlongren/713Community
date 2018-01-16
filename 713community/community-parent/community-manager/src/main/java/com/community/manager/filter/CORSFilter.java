package com.community.manager.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xian
 * @Description: 跨域请求过滤器
 * @Date:create in 2017/10/15 12:55
 */
public class CORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String curOrigin = request.getHeader("Host");
//        LOGGER.info("当前访问来源是：{}", curOrigin);
        // 从列表中获取，可以将数据放入缓存
        List<String> corsOriginList = new ArrayList<String>();
        corsOriginList.add("http://manager.community.com");
        corsOriginList.add("http://static.community.com");
//        if (corsOriginList.contains(curOrigin)) {
//            response.setHeader("Access-Control-Allow-Origin", curOrigin);
//        } else {
//            return ;
//        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}