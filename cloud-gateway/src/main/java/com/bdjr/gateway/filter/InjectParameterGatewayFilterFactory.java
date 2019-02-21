package com.bdjr.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
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
 * @date: 2019/2/20 上午11:29
 * @mofified By:
 */
@Component
public class InjectParameterGatewayFilterFactory extends AbstractGatewayFilterFactory<InjectParameterGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(InjectParameterGatewayFilterFactory.class);

    public InjectParameterGatewayFilterFactory() {
        super(Config.class);
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            return handleParameter(exchange,chain);
        };
    }

    /**
     * @description：处理请求全局参数
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/21 上午11:57
     * @mofified By:
     */
    private Mono<Void> handleParameter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange);
    }
}
