package com.example.demo.container.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author admin
 * @date 2019-2-26 10:04
 */
@Order(1)
@WebFilter(urlPatterns = "/*",filterName = "ReuestFilter")
public class ReuestFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(ReuestFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("---------初始化过滤器-----------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("--------------调用过滤器---------------------");
        RequestWrap requestWrap = new RequestWrap((HttpServletRequest)request);
        chain.doFilter(requestWrap, response);
    }

    @Override
    public void destroy() {

    }
}
