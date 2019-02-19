package com.bdjr.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

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
            JSONObject message = new JSONObject();
            message.put("status", HttpStatus.UNAUTHORIZED);
            message.put("data", "鉴权失败");
            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bits);
            //指定编码，否则在浏览器中会中文乱码
            exchange.getResponse().getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            //当请求不携带Token或者token为空时，直接设置请求状态码为401，返回
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return -400;
    }
}
