package com.bdjr.client.app.intercepter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.intercepter、
 * @email: cy880708@163.com
 * @date: 2019/3/5 下午6:24
 * @mofified By:
 */
@Component
@WebFilter(filterName = "clientAppFilter", urlPatterns = "/*")
public class ClientAppFilter implements Filter {

    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(ClientAppFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //访问地址
        String uri = request.getRequestURI();
        if("自定义请求URL".equals(uri) || "自定义请求URL".equals(uri)) {
            String token = request.getParameter("token");
            if(StringUtils.isEmpty(token)) {
                HashMap hashMap = new HashMap(request.getParameterMap());
                hashMap.put("token", "修改了个值");
                HttpServletRequest req =  request;
                ParameterRequestWrapper wrapRequest = new ParameterRequestWrapper(req, hashMap);
                request.getParameterMap();
                request = wrapRequest;
            }
        }
        logger.info("client - app filter —— ClientAppFilter.java 。 。 。 。 。 。 。 ");

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }

}
