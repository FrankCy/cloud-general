package com.bdjr.gateway;

import com.bdjr.gateway.filter.CustomGatewayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.gateway、
 * @email: cy880708@163.com
 * @date: 2019/2/11 下午6:00
 * @mofified By:
 */
@SpringBootApplication
public class GatewayApplication {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/**")
                        .filters(f -> f.filter(new CustomGatewayFilter()))
                        .uri("http://127.0.0.1:8081")
                        .id("custom_filter")
                )
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
