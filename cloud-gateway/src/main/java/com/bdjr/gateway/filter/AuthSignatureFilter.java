package com.bdjr.gateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.gateway.filter、
 * @email: cy880708@163.com
 * @date: 2019/2/14 上午10:42
 * @mofified By:
 */
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {

    private static final Log logger = LogFactory.getLog(AuthSignatureFilter.class);

    private static final String COUNT_START_TIME = "countStartTime";

    /**
     * 拦截请求，获取authToken，并校验，设置
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("authToken");
        if (null==token  || token.isEmpty()) {
            //当请求不携带Token或者token为空时，直接设置请求状态码为401，返回
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return -400;
    }
}
